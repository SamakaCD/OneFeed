package com.ivansadovyi.domain.app.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.plugin.PluginInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppInitUseCase(
		private val pluginInteractor: PluginInteractor,
		private val feedItemsInteractor: FeedItemsInteractor
) : UseCase<Unit> {

	override suspend fun execute() = withContext(Dispatchers.IO) {
		pluginInteractor.restoreAuthorizations()
		feedItemsInteractor.refresh()
	}
}
