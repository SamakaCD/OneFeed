package com.ivansadovyi.domain.plugin

import com.ivansadovyi.sdk.auth.OAuthParams

interface PluginAuthorizationCallbacks {

	fun onReceiveOAuthParams(params: OAuthParams)

	fun onUnsupportedAuthorizationMethod()
}
