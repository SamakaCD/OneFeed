package com.ivansadovyi.domain.app.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.app.AppVersionRepository
import com.ivansadovyi.domain.log.LoggingInteractor
import com.ivansadovyi.domain.plugin.PluginInteractor

class PerformFirstLaunchSetupUseCase(
		private val currentAppVersion: Int,
		private val loggingInteractor: LoggingInteractor,
		private val pluginInteractor: PluginInteractor,
		private val appVersionRepository: AppVersionRepository
) : UseCase<Unit> {

	override suspend fun execute() {
		loggingInteractor.debug(this, "Invoking first run setup")
		pluginInteractor.setupDefaultBuiltInPluginAuthorizations()
		appVersionRepository.setAppVersion(currentAppVersion)
	}
}