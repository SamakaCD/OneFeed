package com.ivansadovyi.domain.plugin.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationsDao

class ResetAuthorizationsUseCase(
		private val pluginStore: PluginStore,
		private val pluginAuthorizationsDao: PluginAuthorizationsDao,
		private val feedItemsInteractor: FeedItemsInteractor
) : UseCase<Unit> {

	override suspend fun execute() {
		pluginStore.clear()
		pluginAuthorizationsDao.clear()

		// Contents of feed should be reloaded since plugins were changed
		feedItemsInteractor.clear()
		feedItemsInteractor.refresh()
	}
}
