package com.ivansadovyi.domain.plugin.auth

import com.ivansadovyi.sdk.auth.AuthorizationHandler
import com.ivansadovyi.sdk.auth.AuthorizationParams

interface AuthorizationResponseProcessor {

	fun process(response: String, authParams: AuthorizationParams, authorizationHandler: AuthorizationHandler): PluginAuthorizationProcessingResult
}
