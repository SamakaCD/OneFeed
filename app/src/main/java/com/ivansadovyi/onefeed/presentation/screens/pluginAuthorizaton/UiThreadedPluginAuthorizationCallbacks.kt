package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

import android.os.Handler
import android.os.Looper
import com.ivansadovyi.domain.plugin.PluginAuthorizationCallbacks
import com.ivansadovyi.sdk.auth.OAuthParams

class UiThreadedPluginAuthorizationCallbacks(private val callbacks: PluginAuthorizationCallbacks) : PluginAuthorizationCallbacks {

	private val handler = Handler(Looper.getMainLooper())

	override fun onReceiveOAuthParams(params: OAuthParams) {
		handler.post {
			callbacks.onReceiveOAuthParams(params)
		}
	}

	override fun onUnsupportedAuthorizationMethod() {
		handler.post {
			callbacks.onUnsupportedAuthorizationMethod()
		}
	}
}
