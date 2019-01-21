package com.ivansadovyi.domain.plugin.auth

import com.ivansadovyi.sdk.auth.AuthorizationHandler
import com.ivansadovyi.sdk.auth.OAuthAuthorizationHandler

object AuthorizationResponseProcessorFactory {

	fun create(authorizationHandler: AuthorizationHandler): AuthorizationResponseProcessor {
		return when (authorizationHandler) {
			is OAuthAuthorizationHandler -> OAuthAuthorizationResponseProcessor()
			else -> throw IllegalArgumentException("Cannot create AuthorizationResponseProcessor " +
					"for authorizationHandler of type ${authorizationHandler::javaClass.name}")
		}
	}
}
