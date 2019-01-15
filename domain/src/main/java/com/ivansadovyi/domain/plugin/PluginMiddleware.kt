package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.plugin.PluginActions.StartAuthorizationActions.*
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorActions.InstantiatePluginActions.InstantiatePlugin
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorActions.InstantiatePluginActions.InstantiatePluginSucceeded
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorStore
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.OneFeedPluginParams
import com.ivansadovyi.sdk.auth.AuthorizationState
import com.ivansadovyi.sdk.auth.OAuthAuthorizationHandler
import com.ivansadovyi.truba.ActionDispatcher
import com.ivansadovyi.truba.Middleware
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginMiddleware @Inject constructor(
		private val pluginDescriptorStore: PluginDescriptorStore
) : Middleware<Any> {

	private fun startAuthorization(busDispatcher: ActionDispatcher<Any>, pluginDescriptor: OneFeedPluginDescriptor) {
		val pluginParams = OneFeedPluginParams.Builder()
				.setAuthorizationState(AuthorizationState.AUTHORIZING)
				.setDescriptor(pluginDescriptor)
				.build()

		busDispatcher.dispatch(InstantiatePlugin(pluginDescriptor, pluginParams))
		pluginDescriptorStore.observable
				.filter { it is InstantiatePluginSucceeded }
				.firstOrError()
				.map { it as InstantiatePluginSucceeded }
				.subscribeBy {
					val authorizationHandler = it.instance.authorizationHandler
					when (authorizationHandler) {
						is OAuthAuthorizationHandler -> {
							val oauthParams = authorizationHandler.onRequestOAuthParams()
							busDispatcher.dispatch(StartAuthorizationSucceeded(pluginDescriptor, it.instance, oauthParams))
						}
						else -> {
							val throwable = IllegalArgumentException("Authorization handler with type " +
									"${authorizationHandler.javaClass.name} of plugin with descriptor " +
									"[$pluginDescriptor] is not supported")

							busDispatcher.dispatch(StartAuthorizationFailed(pluginDescriptor, throwable))
						}
					}
				}
	}

	override fun onAction(action: Any, busDispatcher: ActionDispatcher<Any>, nextMiddlewareDispatcher: ActionDispatcher<Any>) {
		when (action) {
			is StartAuthorization -> startAuthorization(busDispatcher, action.pluginDescriptor)
		}

		nextMiddlewareDispatcher.dispatch(action)
	}
}
