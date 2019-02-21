package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.oauth

interface OAuthView {

	fun finish()

	fun loadUrl(url: String)

	fun showWebView()
}
