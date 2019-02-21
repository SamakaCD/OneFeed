package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.oauth

import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.onefeed.presentation.GenericExceptionHandler
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.AuthorizationParams
import com.ivansadovyi.sdk.auth.OAuthParams
import kotlinx.coroutines.*
import javax.inject.Inject

class OAuthPresenter @Inject constructor(
		private val view: OAuthView,
		private val exceptionHandler: GenericExceptionHandler,
		private val pluginInteractor: PluginInteractor
) {

	private lateinit var pluginDescriptor: OneFeedPluginDescriptor

	private val job = SupervisorJob()
	private val coroutineScope = CoroutineScope(Dispatchers.Main + job)
	private val loadingStartTime = System.currentTimeMillis()

	fun onInit(pluginDescriptor: OneFeedPluginDescriptor, authParams: AuthorizationParams) {
		this.pluginDescriptor = pluginDescriptor
		val authParams = authParams as OAuthParams
		view.loadUrl(authParams.authUrl)
	}

	fun onDestroy() {
		job.cancel()
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
		coroutineScope.launch {
			val timeElapsed = System.currentTimeMillis() - loadingStartTime
			if (timeElapsed < MIN_LOADING_TIME) {
				delay(MIN_LOADING_TIME - timeElapsed)
			}

			view.showWebView()
		}
	}

	companion object {
		private const val MIN_LOADING_TIME = 1500
	}
}
