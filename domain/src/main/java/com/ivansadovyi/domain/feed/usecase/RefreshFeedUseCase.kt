package com.ivansadovyi.domain.feed.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.FeedItemRepository
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.domain.log.LoggingInteractor
import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.domain.plugin.PluginStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RefreshFeedUseCase(
		private val feedItemsStore: FeedItemsStore,
		private val feedItemsInteractor: FeedItemsInteractor,
		private val pluginStore: PluginStore,
		private val pluginInteractor: PluginInteractor,
		private val loggingInteractor: LoggingInteractor,
		private val feedItemRepository: FeedItemRepository
) : UseCase<Unit> {

	override suspend fun execute() = withContext(Dispatchers.IO) {
		loggingInteractor.debug(this@RefreshFeedUseCase, "Refreshing feed")
		feedItemsStore.startRefreshing()
		val newItems = mutableListOf<BundledFeedItem>()
		val caughtExceptions = mutableListOf<Throwable>()
		for (plugin in pluginStore.getAuthorizedPlugins()) {
			val pluginClassName = plugin.descriptor.className
			try {
				loggingInteractor.debug(this@RefreshFeedUseCase, "Refreshing $pluginClassName")
				val items = pluginInteractor.refresh(plugin)
				var itemsCounter = 0
				for (item in items) {
					newItems += BundledFeedItem(item, pluginClassName)
					itemsCounter++
				}
				loggingInteractor.debug(this@RefreshFeedUseCase, "Got $itemsCounter items from $pluginClassName")
			} catch (throwable: Throwable) {
				loggingInteractor.warning(this@RefreshFeedUseCase,
						"${throwable.javaClass.simpleName} when refreshing $plugin")
				caughtExceptions += throwable
				continue
			}
		}

		// Clear feed only when it was loaded successfully
		// TODO: clear all feeds except failed
		if (caughtExceptions.isEmpty()) {
			feedItemRepository.clearAndPutItems(newItems)
		} else {
			feedItemRepository.putFeedItems(newItems)
		}

		feedItemsStore.finishRefreshing()

		// Throw caught exceptions if they have occurred
		// TODO: add throwing multiple exceptions using CompositeException
		if (caughtExceptions.isNotEmpty()) {
			throw caughtExceptions.first()
		}
	}
}