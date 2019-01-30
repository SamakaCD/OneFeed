package com.ivansadovyi.domain.plugin.auth

import com.ivansadovyi.sdk.auth.AuthorizationParams
import com.ivansadovyi.sdk.auth.OAuthAuthorizationHandler
import com.ivansadovyi.sdk.auth.OAuthParams

class OAuthAuthorizationProcessor(private val authorizationHandler: OAuthAuthorizationHandler) : AuthorizationProcessor {

	override suspend fun requestAuthorizationParams(): AuthorizationParams {
		return authorizationHandler.onRequestOAuthParams()
	}

	override suspend fun processResponse(response: String, authParams: AuthorizationParams): PluginAuthorizationProcessingResult {
		val authParams = authParams as OAuthParams
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
