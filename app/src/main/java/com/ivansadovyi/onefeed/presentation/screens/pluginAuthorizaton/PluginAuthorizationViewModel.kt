package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.domain.plugin.auth.UnsupportedPluginAuthorizationMethodException
import com.ivansadovyi.onefeed.BR
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.PluginAuthorizationState.LOADING
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.PluginAuthorizationState.READY
import com.ivansadovyi.onefeed.presentation.utils.ObservableField
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.OAuthParams
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy

class PluginAuthorizationViewModel(
		private val pluginDescriptor: OneFeedPluginDescriptor,
		private val pluginStore: PluginStore,
		private val view: PluginAuthorizationView
) : BaseObservable() {

	@get:Bindable
	var loading by ObservableField(fieldId = BR.loading, value = true)

	@Bindable("loading")
	fun getState(): PluginAuthorizationState {
		return if (loading) LOADING else READY
	}

	init {
		pluginStore.startAuthorization(pluginDescriptor)
				.observeOn(AndroidSchedulers.mainThread())
				.map { it as OAuthParams }
				.subscribeBy(
						onSuccess = {
							view.loadUrl(it.authUrl)
						},
						onError = {
							if (it is UnsupportedPluginAuthorizationMethodException) {
								view.showUnsupportedAuthorizationMethodError()
							}
						}
				)
	}

	fun onRedirect(url: String) {
		pluginStore.processAuthorizationResponse(pluginDescriptor, response = url)
				.observeOn(AndroidSchedulers.mainThread())
				.filter { it }
				.subscribeBy(
						onSuccess = {
							view.finish()
						}
				)
	}

	fun onFinishLoading() {
		loading = false
	}
}
