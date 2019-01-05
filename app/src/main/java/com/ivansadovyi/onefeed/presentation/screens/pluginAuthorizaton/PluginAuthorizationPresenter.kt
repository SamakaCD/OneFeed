package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

import com.ivansadovyi.domain.plugin.PluginAuthorizationCallbacks
import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.OAuthParams
import javax.inject.Inject

class PluginAuthorizationPresenter @Inject constructor(
		private val view: PluginAuthorizationView,
		private val pluginStore: PluginStore
) : PluginAuthorizationCallbacks {

	private var callbackUrl = ""
	private lateinit var pluginDescriptor: OneFeedPluginDescriptor

	fun onInit(pluginDescriptor: OneFeedPluginDescriptor) {
		this.pluginDescriptor = pluginDescriptor
		val callbacks = UiThreadedPluginAuthorizationCallbacks(this)
		pluginStore.startAuthorization(pluginDescriptor, callbacks).subscribe()
	}

	fun onLoadUrl(url: String): Boolean {
		if (url.startsWith(callbackUrl)) {
			pluginStore.processAuthorizationResponse(this.pluginDescriptor, url).subscribe()
			return true
		}

		return false
	}

	override fun onReceiveOAuthParams(params: OAuthParams) {
		callbackUrl = params.callbackUrl
		view.loadUrl(params.authUrl)
	}

	override fun onUnsupportedAuthorizationMethod() {
		view.showUnsupportedAuthorizationMethodError()
	}
}
