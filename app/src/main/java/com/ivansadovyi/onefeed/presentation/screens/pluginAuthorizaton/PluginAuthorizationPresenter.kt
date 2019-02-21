package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.domain.plugin.auth.UnsupportedPluginAuthorizationMethodException
import com.ivansadovyi.onefeed.presentation.GenericExceptionHandler
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import kotlinx.coroutines.*
import javax.inject.Inject

class PluginAuthorizationPresenter @Inject constructor(
		private val view: PluginAuthorizationView,
		private val router: PluginAuthorizationRouter,
		private val pluginInteractor: PluginInteractor,
		private val exceptionHandler: GenericExceptionHandler
) {

	private val job = SupervisorJob()
	private val coroutineScope = CoroutineScope(Dispatchers.Main + job)
	private val loadingStartTime = System.currentTimeMillis()

	fun onInit(pluginDescriptor: OneFeedPluginDescriptor) {
		router.navigateToPluginLoading()
		coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
			try {
				val timeElapsed = System.currentTimeMillis() - loadingStartTime
				if (timeElapsed < MIN_LOADING_TIME) {
					delay(MIN_LOADING_TIME - timeElapsed)
				}

				val authParams = pluginInteractor.startPluginAuthorization(pluginDescriptor)
				router.navigateToAuthorization(pluginDescriptor, authParams)
			} catch (_: UnsupportedPluginAuthorizationMethodException) {
				view.showUnsupportedAuthorizationMethodError()
			}
		}
	}

	fun onDestroy() {
		job.cancel()
	}

	companion object {
		private const val MIN_LOADING_TIME = 500
	}
}
