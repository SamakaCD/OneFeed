package com.ivansadovyi.domain.plugin.auth

import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorStore
import com.ivansadovyi.domain.utils.ObservableStore
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.OAuthAuthorizationHandler
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginAuthorizationStoreImpl @Inject constructor(
		private val pluginDescriptorStore: PluginDescriptorStore
) : ObservableStore<PluginAuthorizationStore>(), PluginAuthorizationStore {

	override val authorizations: List<PluginAuthorization> = emptyList()

	override fun startAuthorization(pluginDescriptor: OneFeedPluginDescriptor, callbacks: PluginAuthorizationCallbacks): Completable {
		return pluginDescriptorStore.instantiatePlugin(pluginDescriptor)
				.doOnSuccess { plugin ->
					val authorizationHandler = plugin.authorizationHandler
					when (authorizationHandler) {
						is OAuthAuthorizationHandler -> {
							val authUrl = authorizationHandler.onRequestAuthorizationUrl()
							callbacks.onLaunchOAuthUrl(authUrl)
						}
						else -> callbacks.onUnsupportedAuthorizationMethod()
					}
				}
				.ignoreElement()
	}
}
