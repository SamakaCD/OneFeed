package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

interface PluginAuthorizationView {

	fun finishAuthorization()

	fun loadUrl(url: String)

	fun showUnsupportedAuthorizationMethodError()
}
