package com.ivansadovyi.domain.plugin.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.domain.plugin.auth.PluginAuthorization
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationsDao
import com.ivansadovyi.sdk.OneFeedPluginParams
import com.ivansadovyi.sdk.auth.AuthorizationState

class RestorePluginAuthorizationsUseCase(
		private val pluginStore: PluginStore,
		private val pluginLoader: PluginLoader,
		private var pluginAuthorizationsDao: PluginAuthorizationsDao
) : UseCase<Unit> {

	override suspend fun execute() {
		pluginAuthorizationsDao.getPluginAuthorizations().forEach {
			instantiateAndStorePlugin(it)
		}
	}

	private suspend fun instantiateAndStorePlugin(pluginAuthorization: PluginAuthorization) {
		val plugin = pluginLoader.instantiate(pluginAuthorization.pluginClassName)
		val params = createPluginParams(pluginAuthorization.authorization)
		plugin.onInit(params)
		pluginStore.putPlugin(plugin)
	}

	private fun createPluginParams(authorization: String): OneFeedPluginParams {
		return OneFeedPluginParams.Builder()
				.setAuthorization(authorization)
				.setAuthorizationState(AuthorizationState.AUTHORIZED)
				.build()
	}
}
