package com.ivansadovyi.domain.app

import com.ivansadovyi.domain.app.usecase.AppInitUseCase
import com.ivansadovyi.domain.app.usecase.PerformFirstLaunchSetupUseCase
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.log.LoggingInteractor
import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorInteractor
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppInteractor @Inject constructor(
		private val currentAppVersion: Int,
		private val pluginInteractor: Lazy<PluginInteractor>,
		private val pluginDescriptorInteractor: Lazy<PluginDescriptorInteractor>,
		private val feedItemsInteractor: Lazy<FeedItemsInteractor>,
		private val loggingInteractor: Lazy<LoggingInteractor>,
		private val appVersionRepository: Lazy<AppVersionRepository>
) {

	suspend fun init() {
		AppInitUseCase(
				appInteractor = this,
				pluginInteractor = pluginInteractor.get(),
				pluginDescriptorInteractor = pluginDescriptorInteractor.get(),
				loggingInteractor = loggingInteractor.get(),
				appVersionRepository = appVersionRepository.get(),
				feedItemsInteractor = feedItemsInteractor.get()
		).execute()
	}

	suspend fun performFirstLaunchSetup() {
		PerformFirstLaunchSetupUseCase(
				currentAppVersion = currentAppVersion,
				loggingInteractor = loggingInteractor.get(),
				pluginInteractor = pluginInteractor.get(),
				appVersionRepository = appVersionRepository.get()
		).execute()
	}
}
