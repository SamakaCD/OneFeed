package com.ivansadovyi.domain.plugin.auth

interface PluginAuthorizationCallbacks {

	fun onLaunchOAuthUrl(url: String)

	fun onUnsupportedAuthorizationMethod()
}
