package com.ivansadovyi.domain.plugin.auth

import com.ivansadovyi.sdk.auth.AuthorizationParams

interface AuthorizationProcessor {

	suspend fun requestAuthorizationParams(): AuthorizationParams

	suspend fun processResponse(response: String, authParams: AuthorizationParams): PluginAuthorizationProcessingResult
}
