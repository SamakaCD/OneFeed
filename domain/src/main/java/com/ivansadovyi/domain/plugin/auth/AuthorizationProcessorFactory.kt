package com.ivansadovyi.domain.plugin.auth

import com.ivansadovyi.domain.plugin.auth.empty.EmptyAuthorizationProcessor
import com.ivansadovyi.domain.plugin.auth.oauth.OAuthAuthorizationProcessor
import com.ivansadovyi.sdk.auth.AuthorizationHandler
import com.ivansadovyi.sdk.auth.EmptyAuthorizationHandler
import com.ivansadovyi.sdk.auth.OAuthAuthorizationHandler

object AuthorizationProcessorFactory {

	fun create(authorizationHandler: AuthorizationHandler): AuthorizationProcessor {
		return when (authorizationHandler) {
			is OAuthAuthorizationHandler -> OAuthAuthorizationProcessor(authorizationHandler)
			is EmptyAuthorizationHandler -> EmptyAuthorizationProcessor()
			else -> throw UnsupportedPluginAuthorizationMethodException()
		}
	}
}
