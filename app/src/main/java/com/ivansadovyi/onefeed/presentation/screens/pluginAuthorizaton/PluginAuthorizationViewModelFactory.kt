package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import javax.inject.Inject
import javax.inject.Provider

class PluginAuthorizationViewModelFactory @Inject constructor(
		private val pluginInteractorProvider: Provider<PluginInteractor>,
		private val pluginAuthorizationViewProvider: Provider<PluginAuthorizationView>
) {

	fun create(pluginDescriptor: OneFeedPluginDescriptor): PluginAuthorizationViewModel {
		return PluginAuthorizationViewModel(
				pluginDescriptor,
				pluginInteractor = pluginInteractorProvider.get(),
				view = pluginAuthorizationViewProvider.get()
		)
	}
}
