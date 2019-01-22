package com.ivansadovyi.domain.plugin.auth

import com.ivansadovyi.sdk.auth.AuthorizationHandler
import com.ivansadovyi.sdk.auth.AuthorizationParams
import com.ivansadovyi.sdk.auth.OAuthAuthorizationHandler
import com.ivansadovyi.sdk.auth.OAuthParams

class OAuthAuthorizationResponseProcessor : AuthorizationResponseProcessor {

	override fun process(response: String, authParams: AuthorizationParams, authorizationHandler: AuthorizationHandler): PluginAuthorizationProcessingResult {
		val authParams = authParams as OAuthParams
		val authorizationHandler = authorizationHandler as OAuthAuthorizationHandler
		return if (response.startsWith(authParams.callbackUrl)) {
			PluginAuthorizationProcessingResult(
					authorization = authorizationHandler.processOAuthResponse(response),
					wasProcessed = true
			)
		} else {
			PluginAuthorizationProcessingResult(
					authorization = null,
					wasProcessed = false
			)
		}
	}
}
