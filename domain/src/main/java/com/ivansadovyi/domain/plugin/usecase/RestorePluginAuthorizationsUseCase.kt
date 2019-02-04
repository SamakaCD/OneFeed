package com.ivansadovyi.domain.plugin.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.domain.plugin.auth.PluginAuthorization
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationRepository
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorInteractor
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.OneFeedPluginParams
import com.ivansadovyi.sdk.auth.AuthorizationState

class RestorePluginAuthorizationsUseCase(
		private val pluginStore: PluginStore,
		private val pluginDescriptorInteractor: PluginDescriptorInteractor,
		private val pluginLoader: PluginLoader,
		private var pluginAuthorizationRepository: PluginAuthorizationRepository
) : UseCase<Unit> {

	override suspend fun execute() {
		pluginAuthorizationRepository.getPluginAuthorizations().forEach {
			instantiateAndStorePlugin(it)
		}
	}

	private suspend fun instantiateAndStorePlugin(pluginAuthorization: PluginAuthorization) {
		val pluginDescriptor = pluginLoader.getDescriptorByClassName(pluginAuthorization.pluginClassName)
		val params = createPluginParams(pluginAuthorization.authorization, pluginDescriptor)
		val plugin = pluginDescriptorInteractor.instantiate(pluginDescriptor, params)
		plugin.onInit(params)
		pluginStore.putPlugin(plugin)
	}

	private fun createPluginParams(authorization: String, pluginDescriptor: OneFeedPluginDescriptor): OneFeedPluginParams {
		return OneFeedPluginParams.Builder()
				.setAuthorization(authorization)
				.setAuthorizationState(AuthorizationState.AUTHORIZED)
				.setDescriptor(pluginDescriptor)
				.build()
	}
}
