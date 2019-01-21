package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

interface PluginAuthorizationView {

	fun finish()

	fun loadUrl(url: String)

	fun showUnsupportedAuthorizationMethodError()
}
