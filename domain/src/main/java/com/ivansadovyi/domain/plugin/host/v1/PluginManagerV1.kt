package com.ivansadovyi.domain.plugin.host.v1

import com.ivansadovyi.domain.app.AppRouter
import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.PluginManager
import com.ivansadovyi.sdk.auth.AuthorizationState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginManagerV1 @Inject constructor(
		private val pluginStore: PluginStore,
		private val appRouter: AppRouter
) : PluginManager {

	override fun getAuthorizedPluginDescriptors(): List<OneFeedPluginDescriptor> {
		return pluginStore.plugins
				.filter { it.authorizationState == AuthorizationState.AUTHORIZED }
				.map { it.descriptor }
	}

	override fun startAuthorization(descriptor: OneFeedPluginDescriptor) {
		appRouter.navigateToPluginAuthorization(descriptor)
	}
}