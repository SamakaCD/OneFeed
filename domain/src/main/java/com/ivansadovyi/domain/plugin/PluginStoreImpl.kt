package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorStore
import com.ivansadovyi.domain.utils.ObservableStore
import com.ivansadovyi.domain.utils.ObservableValue
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.OneFeedPluginParams
import com.ivansadovyi.sdk.auth.AuthorizationState
import com.ivansadovyi.sdk.auth.OAuthAuthorizationHandler
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginStoreImpl @Inject constructor(
		private val pluginDescriptorStore: PluginDescriptorStore
) : ObservableStore<PluginStore>(), PluginStore {

	override var plugins: MutableList<OneFeedPlugin> by ObservableValue(defaultValue = mutableListOf())

	override fun startAuthorization(pluginDescriptor: OneFeedPluginDescriptor, callbacks: PluginAuthorizationCallbacks): Completable {
		val pluginParams = OneFeedPluginParams.Builder()
				.setAuthorizationState(AuthorizationState.AUTHORIZING)
				.setDescriptor(pluginDescriptor)
				.build()

		return pluginDescriptorStore.instantiatePlugin(pluginDescriptor, pluginParams)
				.subscribeOn(Schedulers.computation())
				.doOnSuccess { plugin ->
					val authorizationHandler = plugin.authorizationHandler
					when (authorizationHandler) {
						is OAuthAuthorizationHandler -> {
							callbacks.onReceiveOAuthParams(authorizationHandler.onRequestOAuthParams())
						}
						else -> {
							callbacks.onUnsupportedAuthorizationMethod()
							return@doOnSuccess
						}
					}

					plugins.add(plugin)
				}
				.ignoreElement()
	}

	override fun processAuthorizationResponse(pluginDescriptor: OneFeedPluginDescriptor, response: String): Completable {
		return Completable.fromAction {
			val authorizingPlugin = plugins.find { it.descriptor == pluginDescriptor }
					?: throw IllegalArgumentException("Cannot find instance of plugin with descriptor [$pluginDescriptor]")

			if (authorizingPlugin.authorizationState != AuthorizationState.AUTHORIZING) {
				throw IllegalArgumentException("Non-authorizing plugin with descriptor [$pluginDescriptor]" +
						" can not process authorization response")
			}

			val authorizationHandler = authorizingPlugin.authorizationHandler
			val authorization = when (authorizationHandler) {
				is OAuthAuthorizationHandler -> authorizationHandler.processOAuthResponse(response)
				else -> throw IllegalArgumentException("Authorization handler with type " +
						"${authorizationHandler.javaClass.name} is not supported")
			}

			val newParams = authorizingPlugin.params.newBuilder()
					.setAuthorization(authorization)
					.setAuthorizationState(AuthorizationState.AUTHORIZED)
					.build()

			authorizingPlugin.onAuthorizationStateChanged(newParams)
			notifyChange()
		}.subscribeOn(Schedulers.computation())
	}
}
