package com.ivansadovyi.domain.plugin.auth

import com.ivansadovyi.sdk.auth.AuthorizationHandler
import com.ivansadovyi.sdk.auth.OAuthAuthorizationHandler

object AuthorizationProcessorFactory {

	fun create(authorizationHandler: AuthorizationHandler): AuthorizationProcessor {
		return when (authorizationHandler) {
			is OAuthAuthorizationHandler -> OAuthAuthorizationProcessor(authorizationHandler)
			else -> throw UnsupportedPluginAuthorizationMethodException()
		}
	}
}
