package com.ivansadovyi.domain.feed

import com.ivansadovyi.domain.feed.usecase.LoadMoreFeedUseCase
import com.ivansadovyi.domain.feed.usecase.RefreshFeedUseCase
import com.ivansadovyi.domain.plugin.PluginStore
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class FeedItemsInteractorImpl @Inject constructor(
		private val feedItemsStore: Lazy<FeedItemsStore>,
		private val pluginStore: Lazy<PluginStore>,
		private val feedItemsDao: Provider<FeedItemsDao>
) : FeedItemsInteractor {

	override suspend fun refresh() {
		RefreshFeedUseCase(
				feedItemsStore = feedItemsStore.get(),
				pluginStore = pluginStore.get(),
				feedItemsDao = feedItemsDao.get()
		).execute()
	}

	override suspend fun loadMore() {
		LoadMoreFeedUseCase(
				feedItemsStore = feedItemsStore.get(),
				pluginStore = pluginStore.get(),
				feedItemsDao = feedItemsDao.get()
		).execute()
	}
}
