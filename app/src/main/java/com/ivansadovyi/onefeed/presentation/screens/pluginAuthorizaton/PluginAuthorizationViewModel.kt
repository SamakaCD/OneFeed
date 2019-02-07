package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.domain.plugin.auth.UnsupportedPluginAuthorizationMethodException
import com.ivansadovyi.onefeed.BR
import com.ivansadovyi.onefeed.presentation.GenericExceptionHandler
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.PluginAuthorizationState.LOADING
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.PluginAuthorizationState.READY
import com.ivansadovyi.onefeed.presentation.utils.ObservableField
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.OAuthParams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class PluginAuthorizationViewModel(
		private val pluginDescriptor: OneFeedPluginDescriptor,
		private val pluginInteractor: PluginInteractor,
		private val view: PluginAuthorizationView,
		private val exceptionHandler: GenericExceptionHandler
) : BaseObservable() {

	@get:Bindable
	var loading by ObservableField(fieldId = BR.loading, value = true)

	@Bindable("loading")
	fun getState(): PluginAuthorizationState {
		return if (loading) LOADING else READY
	}

	private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

	init {
		coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
			try {
				val authParams = pluginInteractor.startPluginAuthorization(pluginDescriptor) as OAuthParams
				view.loadUrl(authParams.authUrl)
			} catch (_: UnsupportedPluginAuthorizationMethodException) {
				view.showUnsupportedAuthorizationMethodError()
			}
		}
	}

	fun onRedirect(url: String) {
		coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
			val wasResponseProcessed = pluginInteractor.processAuthorizationResponse(pluginDescriptor, response = url)
			if (wasResponseProcessed) {
				view.finish()
			}
		}
	}

	fun onFinishLoading() {
		loading = false
	}
}
