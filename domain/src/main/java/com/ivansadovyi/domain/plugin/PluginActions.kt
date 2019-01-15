package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.utils.truba.ErrorAction
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.AuthorizationParams

sealed class PluginActions {

	sealed class StartAuthorizationActions {

		class StartAuthorization(val pluginDescriptor: OneFeedPluginDescriptor)

		class StartAuthorizationSucceeded(
				val pluginDescriptor: OneFeedPluginDescriptor,
				val instance: OneFeedPlugin,
				val authorizationParams: AuthorizationParams
		)

		class StartAuthorizationFailed(
				val pluginDescriptor: OneFeedPluginDescriptor,
				override val throwable: Throwable
		) : ErrorAction
	}

	sealed class ProcessAuthorizationResponseActions {

		class ProcessAuthorizationResponse(
				val pluginDescriptor: OneFeedPluginDescriptor,
				val response: String
		)

		class ProcessAuthorizationResponseSucceeded(val pluginDescriptor: OneFeedPluginDescriptor)

		class ProcessAuthorizationResponseFailed(
				val pluginDescriptor: OneFeedPluginDescriptor,
				override val throwable: Throwable
		) : ErrorAction
	}
}
