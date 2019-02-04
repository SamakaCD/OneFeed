package com.ivansadovyi.domain.feed

import com.ivansadovyi.domain.feed.usecase.ClearFeedUseCase
import com.ivansadovyi.domain.feed.usecase.LoadMoreFeedUseCase
import com.ivansadovyi.domain.feed.usecase.RefreshFeedUseCase
import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.domain.plugin.PluginStore
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class FeedItemsInteractorImpl @Inject constructor(
		private val feedItemsStore: Lazy<FeedItemsStore>,
		private val pluginStore: Lazy<PluginStore>,
		private val pluginInteractor: Lazy<PluginInteractor>,
		private val feedItemRepository: Provider<FeedItemRepository>
) : FeedItemsInteractor {

	override suspend fun clear() {
		ClearFeedUseCase(
				feedItemRepository = feedItemRepository.get()
		).execute()
	}

	override suspend fun refresh() {
		RefreshFeedUseCase(
				feedItemsStore = feedItemsStore.get(),
				pluginStore = pluginStore.get(),
				pluginInteractor = pluginInteractor.get(),
				feedItemRepository = feedItemRepository.get()
		).execute()
	}

	override suspend fun loadMore() {
		LoadMoreFeedUseCase(
				feedItemsStore = feedItemsStore.get(),
				pluginStore = pluginStore.get(),
				pluginInteractor = pluginInteractor.get(),
				feedItemRepository = feedItemRepository.get()
		).execute()
	}
}
