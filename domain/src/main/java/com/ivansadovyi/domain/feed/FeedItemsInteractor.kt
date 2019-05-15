package com.ivansadovyi.domain.feed

import com.ivansadovyi.domain.feed.usecase.ClearFeedUseCase
import com.ivansadovyi.domain.feed.usecase.LikeFeedItemUseCase
import com.ivansadovyi.domain.feed.usecase.LoadMoreFeedUseCase
import com.ivansadovyi.domain.feed.usecase.RefreshFeedUseCase
import com.ivansadovyi.domain.log.LoggingInteractor
import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.domain.plugin.PluginStore
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedItemsInteractor @Inject constructor(
		private val feedItemsStore: Lazy<FeedItemsStore>,
		private val pluginStore: Lazy<PluginStore>,
		private val pluginInteractor: Lazy<PluginInteractor>,
		private val loggingInteractor: Lazy<LoggingInteractor>,
		private val feedItemRepository: Lazy<FeedItemRepository>
) {

	suspend fun clear() {
		ClearFeedUseCase(
				loggingInteractor = loggingInteractor.get(),
				feedItemRepository = feedItemRepository.get()
		).execute()
	}

	suspend fun refresh() {
		RefreshFeedUseCase(
				feedItemsStore = feedItemsStore.get(),
				feedItemsInteractor = this,
				loggingInteractor = loggingInteractor.get(),
				pluginStore = pluginStore.get(),
				pluginInteractor = pluginInteractor.get(),
				feedItemRepository = feedItemRepository.get()
		).execute()
	}

	suspend fun loadMore() {
		LoadMoreFeedUseCase(
				feedItemsStore = feedItemsStore.get(),
				pluginStore = pluginStore.get(),
				pluginInteractor = pluginInteractor.get(),
				loggingInteractor = loggingInteractor.get(),
				feedItemRepository = feedItemRepository.get()
		).execute()
	}

	suspend fun likeItem(item: BundledFeedItem) {
		LikeFeedItemUseCase(
				item = item,
				feedItemRepository = feedItemRepository.get()
		).execute()
	}
}
