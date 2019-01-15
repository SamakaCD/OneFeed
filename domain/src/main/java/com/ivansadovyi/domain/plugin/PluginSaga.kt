package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.plugin.PluginActions.StartAuthorizationActions.*
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorActions.InstantiatePluginActions.InstantiatePlugin
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorActions.InstantiatePluginActions.InstantiatePluginSucceeded
import com.ivansadovyi.sdk.OneFeedPluginParams
import com.ivansadovyi.sdk.auth.AuthorizationState
import com.ivansadovyi.sdk.auth.OAuthAuthorizationHandler
import com.ivansadovyi.truba.ActionDispatcher
import com.ivansadovyi.truba.saga.Saga
import com.ivansadovyi.truba.saga.actionBinding
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.rx2.asCoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginSaga @Inject constructor(dispatcher: ActionDispatcher<Any>) : Saga<Any>(dispatcher) {

	private val startAuthorization = actionBinding<StartAuthorization> {
		val pluginParams = OneFeedPluginParams.Builder()
				.setAuthorizationState(AuthorizationState.AUTHORIZING)
				.setDescriptor(pluginDescriptor)
				.build()

		dispatch(InstantiatePlugin(pluginDescriptor, pluginParams))
		val plugin = waitAction<InstantiatePluginSucceeded>().instance
		val authorizationHandler = plugin.authorizationHandler
		when (authorizationHandler) {
			is OAuthAuthorizationHandler -> {
				val oauthParams = runBlocking(Schedulers.io().asCoroutineDispatcher()) {
					authorizationHandler.onRequestOAuthParams()
				}
				dispatch(StartAuthorizationSucceeded(plugin.descriptor, plugin, oauthParams))
			}
			else -> {
				dispatch(StartAuthorizationFailed(
						pluginDescriptor = pluginDescriptor,
						throwable = Throwable("")
				))
				return@actionBinding
			}
		}
	}

	init {
		bindAction(startAuthorization)
	}
}
