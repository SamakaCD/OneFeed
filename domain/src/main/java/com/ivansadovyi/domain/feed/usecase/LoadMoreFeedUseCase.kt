package com.ivansadovyi.domain.feed.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.feed.FeedItemsDao
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.sdk.auth.AuthorizationState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadMoreFeedUseCase(
		private val feedItemsStore: FeedItemsStore,
		private val pluginStore: PluginStore,
		private val feedItemsDao: FeedItemsDao
) : UseCase<Unit> {

	override suspend fun execute() = withContext(Dispatchers.IO) {
		feedItemsStore.loading = true
		pluginStore.plugins
				.asSequence()
				.filter { it.authorizationState == AuthorizationState.AUTHORIZED }
				.map { it.loadNextItems().toList() }
				.forEach { feedItemsDao.putFeedItems(it) }
		feedItemsStore.loading = false
	}
}
