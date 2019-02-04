package com.ivansadovyi.domain.feed.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.feed.FeedItemRepository
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.domain.plugin.PluginStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadMoreFeedUseCase(
		private val feedItemsStore: FeedItemsStore,
		private val pluginStore: PluginStore,
		private val pluginInteractor: PluginInteractor,
		private val feedItemRepository: FeedItemRepository
) : UseCase<Unit> {

	override suspend fun execute() = withContext(Dispatchers.IO) {
		feedItemsStore.loading = true
		for (plugin in pluginStore.getAuthorizedPlugins()) {
			val feedItems = pluginInteractor.loadNextItems(plugin).toList()
			feedItemRepository.putFeedItems(feedItems)
		}
		feedItemsStore.loading = false
	}
}
