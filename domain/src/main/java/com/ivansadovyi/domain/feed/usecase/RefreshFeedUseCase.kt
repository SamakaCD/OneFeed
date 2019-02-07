package com.ivansadovyi.domain.feed.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.FeedItemRepository
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.domain.plugin.PluginStore
import io.reactivex.exceptions.CompositeException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RefreshFeedUseCase(
		private val feedItemsStore: FeedItemsStore,
		private val feedItemsInteractor: FeedItemsInteractor,
		private val pluginStore: PluginStore,
		private val pluginInteractor: PluginInteractor,
		private val feedItemRepository: FeedItemRepository
) : UseCase<Unit> {

	override suspend fun execute() = withContext(Dispatchers.IO) {
		feedItemsStore.refreshing = true
		feedItemsStore.loading = true
		val newItems = mutableListOf<BundledFeedItem>()
		val caughtExceptions = mutableListOf<Throwable>()
		for (plugin in pluginStore.getAuthorizedPlugins()) {
			try {
				val pluginClassName = plugin.descriptor.className
				val items = pluginInteractor.refresh(plugin)
				for (item in items) {
					newItems += BundledFeedItem(item, pluginClassName)
				}
			} catch (throwable: Throwable) {
				caughtExceptions += throwable
				continue
			}
		}

		// Clear feed only when it was loaded successfully
		// TODO: clear all feeds except failed
		if (caughtExceptions.isEmpty()) {
			feedItemsInteractor.clear()
		}

		feedItemRepository.putFeedItems(newItems)
		feedItemsStore.loading = false
		feedItemsStore.refreshing = false

		// Throw caught exceptions if they have occurred
		if (caughtExceptions.size == 1) {
			throw caughtExceptions.first()
		} else if (caughtExceptions.size >= 1) {
			throw CompositeException(caughtExceptions)
		}
	}
}