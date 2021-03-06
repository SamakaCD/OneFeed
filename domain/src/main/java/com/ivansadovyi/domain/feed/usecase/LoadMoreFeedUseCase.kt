package com.ivansadovyi.domain.feed.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.FeedItemRepository
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.domain.log.LoggingInteractor
import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.domain.plugin.PluginStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadMoreFeedUseCase(
		private val feedItemsStore: FeedItemsStore,
		private val pluginStore: PluginStore,
		private val pluginInteractor: PluginInteractor,
		private val loggingInteractor: LoggingInteractor,
		private val feedItemRepository: FeedItemRepository
) : UseCase<Unit> {

	override suspend fun execute() = withContext(Dispatchers.IO) {
		loggingInteractor.debug(this@LoadMoreFeedUseCase, "Loading more feed")
		feedItemsStore.startLoading()
		for (plugin in pluginStore.getAuthorizedPlugins()) {
			val pluginClassName = plugin.descriptor.className
			loggingInteractor.debug(this@LoadMoreFeedUseCase, "Loading from $pluginClassName")
			pluginInteractor.loadNextItems(plugin)
					.map { BundledFeedItem(it, pluginClassName) }
					.toList()
					.let { feedItemRepository.putFeedItems(it) }
		}
		feedItemsStore.finishLoading()
	}
}
