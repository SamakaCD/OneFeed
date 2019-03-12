package com.ivansadovyi.domain.app.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.app.AppInteractor
import com.ivansadovyi.domain.app.AppVersionRepository
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.log.LoggingInteractor
import com.ivansadovyi.domain.plugin.PluginInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppInitUseCase(
		private val appInteractor: AppInteractor,
		private val pluginInteractor: PluginInteractor,
		private val loggingInteractor: LoggingInteractor,
		private val appVersionRepository: AppVersionRepository,
		private val feedItemsInteractor: FeedItemsInteractor
) : UseCase<Unit> {

	override suspend fun execute() = withContext(Dispatchers.IO) {
		if (appVersionRepository.getPreviouslyInstalledAppVersion() == AppVersionRepository.FIRST_LAUNCH_VERSION) {
			appInteractor.performFirstLaunchSetup()
		}

		loggingInteractor.debug(this@AppInitUseCase, "Performing app init")
		pluginInteractor.restoreAuthorizations()
		feedItemsInteractor.refresh()
	}
}
