package com.ivansadovyi.domain.plugin.usecase

import android.app.Application
import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.domain.plugin.auth.AuthorizationProcessorFactory
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorInteractor
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.OneFeedPluginParams
import com.ivansadovyi.sdk.auth.AuthorizationParams
import com.ivansadovyi.sdk.auth.AuthorizationState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StartPluginAuthorizationUseCase(
		private val pluginDescriptor: OneFeedPluginDescriptor,
		private val pluginStore: PluginStore,
		private val pluginDescriptorInteractor: PluginDescriptorInteractor,
		private val application: Application
) : UseCase<AuthorizationParams> {

	override suspend fun execute(): AuthorizationParams = withContext(Dispatchers.IO) {
		val params = createAuthorizingPluginParams(pluginDescriptor)
		val plugin = pluginDescriptorInteractor.instantiate(pluginDescriptor, params)
		val authParams = AuthorizationProcessorFactory.create(plugin.authorizationHandler).requestAuthorizationParams()

		// Store authorization params in cache. It prevents fetching them second time when
		// processing authorization response.
		pluginStore.putAuthorizationParamsIntoCache(plugin, authParams)
		pluginStore.putPlugin(plugin)
		return@withContext authParams
	}

	private fun createAuthorizingPluginParams(pluginDescriptor: OneFeedPluginDescriptor): OneFeedPluginParams {
		return OneFeedPluginParams.Builder()
				.setAuthorizationState(AuthorizationState.AUTHORIZING)
				.setDescriptor(pluginDescriptor)
				.setContext(application)
				.build()
	}
}
