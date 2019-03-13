package com.ivansadovyi.domain.plugin.usecase

import android.app.Application
import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.domain.plugin.auth.PluginAuthorization
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationRepository
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorInteractor
import com.ivansadovyi.sdk.OneFeedHost
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.OneFeedPluginParams
import com.ivansadovyi.sdk.auth.AuthorizationState

class RestorePluginAuthorizationsUseCase(
		private val pluginStore: PluginStore,
		private val pluginInteractor: PluginInteractor,
		private val pluginDescriptorInteractor: PluginDescriptorInteractor,
		private val pluginLoader: PluginLoader,
		private val pluginAuthorizationRepository: PluginAuthorizationRepository,
		private val oneFeedHost: OneFeedHost,
		private val application: Application
) : UseCase<Unit> {

	override suspend fun execute() {
		pluginAuthorizationRepository.getPluginAuthorizations().forEach {
			val plugin = instantiatePlugin(it)
			pluginStore.putPlugin(plugin)
			pluginInteractor.cachePluginIcon(plugin)
		}
	}

	private suspend fun instantiatePlugin(pluginAuthorization: PluginAuthorization): OneFeedPlugin {
		val pluginDescriptor = pluginLoader.getDescriptorByClassName(pluginAuthorization.pluginClassName)
		val params = createPluginParams(pluginAuthorization.authorization, pluginDescriptor)
		val plugin = pluginDescriptorInteractor.instantiate(pluginDescriptor, params)
		plugin.onInit(params)
		return plugin
	}

	private fun createPluginParams(authorization: String, pluginDescriptor: OneFeedPluginDescriptor): OneFeedPluginParams {
		return OneFeedPluginParams.Builder()
				.setAuthorization(authorization)
				.setAuthorizationState(AuthorizationState.AUTHORIZED)
				.setContext(application)
				.setDescriptor(pluginDescriptor)
				.setHost(oneFeedHost)
				.build()
	}
}
