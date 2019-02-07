package com.ivansadovyi.domain.feed.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.FeedItemRepository
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.domain.plugin.PluginStore
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
		for (plugin in pluginStore.getAuthorizedPlugins()) {
			val pluginClassName = plugin.descriptor.className
			val items = pluginInteractor.refresh(plugin)
			for (item in items) {
				newItems.add(BundledFeedItem(item, pluginClassName))
			}
		}
		feedItemsInteractor.clear()
		feedItemRepository.putFeedItems(newItems)
		feedItemsStore.loading = false
		feedItemsStore.refreshing = false
	}
}