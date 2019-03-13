package com.ivansadovyi.domain.plugin.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.log.LoggingInteractor
import com.ivansadovyi.domain.plugin.BuiltInPluginLoader
import com.ivansadovyi.domain.plugin.auth.PluginAuthorization
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationRepository

class SetupDefaultBuiltInPluginAuthorizationsUseCase(
		private val loggingInteractor: LoggingInteractor,
		private val builtInPluginLoader: BuiltInPluginLoader,
		private val pluginAuthorizationRepository: PluginAuthorizationRepository
): UseCase<Unit> {

	override suspend fun execute() {
		loggingInteractor.debug(this, "Setting up built-in plugin authorizations")
		builtInPluginLoader.getDefaultBuiltInPluginDescriptors()
				.map { PluginAuthorization(pluginClassName = it.className, authorization = "") }
				.forEach { pluginAuthorizationRepository.putPluginAuthorization(it) }
	}
}
