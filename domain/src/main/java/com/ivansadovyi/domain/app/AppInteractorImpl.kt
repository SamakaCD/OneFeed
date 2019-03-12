package com.ivansadovyi.domain.app

import com.ivansadovyi.domain.app.usecase.AppInitUseCase
import com.ivansadovyi.domain.app.usecase.PerformFirstLaunchSetupUseCase
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.log.LoggingInteractor
import com.ivansadovyi.domain.plugin.PluginInteractor
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppInteractorImpl @Inject constructor(
		private val pluginInteractor: Lazy<PluginInteractor>,
		private val feedItemsInteractor: Lazy<FeedItemsInteractor>,
		private val loggingInteractor: Lazy<LoggingInteractor>,
		private val appVersionRepository: Lazy<AppVersionRepository>
) : AppInteractor {

	override suspend fun init() {
		AppInitUseCase(
				appInteractor = this,
				pluginInteractor = pluginInteractor.get(),
				loggingInteractor = loggingInteractor.get(),
				appVersionRepository = appVersionRepository.get(),
				feedItemsInteractor = feedItemsInteractor.get()
		).execute()
	}

	override suspend fun performFirstLaunchSetup() {
		PerformFirstLaunchSetupUseCase(
				loggingInteractor = loggingInteractor.get(),
				pluginInteractor = pluginInteractor.get()
		).execute()
	}
}
