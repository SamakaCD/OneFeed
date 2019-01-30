package com.ivansadovyi.domain.app

import com.ivansadovyi.domain.app.usecase.AppInitUseCase
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.plugin.PluginInteractor
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class AppInteractorImpl @Inject constructor(
		private val pluginInteractorProvider: Provider<PluginInteractor>,
		private val feedItemsInteractorProvider: Provider<FeedItemsInteractor>
) : AppInteractor {

	override suspend fun init() {
		AppInitUseCase(
				pluginInteractor = pluginInteractorProvider.get(),
				feedItemsInteractor = feedItemsInteractorProvider.get()
		).execute()
	}
}
