package com.ivansadovyi.domain.plugin.auth.empty

import com.ivansadovyi.domain.plugin.auth.AuthorizationProcessor
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationProcessingResult
import com.ivansadovyi.sdk.auth.AuthorizationParams

class EmptyAuthorizationProcessor : AuthorizationProcessor {

	override suspend fun requestAuthorizationParams(): AuthorizationParams {
		return EmptyAuthorizaionParams()
	}

	override suspend fun processResponse(response: String, authParams: AuthorizationParams): PluginAuthorizationProcessingResult {
		return PluginAuthorizationProcessingResult(authorization = "", wasProcessed = true)
	}
}
