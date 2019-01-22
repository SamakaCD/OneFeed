package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import javax.inject.Inject
import javax.inject.Provider

class PluginAuthorizationViewModelFactory @Inject constructor(
		private val pluginStoreProvider: Provider<PluginStore>,
		private val pluginAuthorizationViewProvider: Provider<PluginAuthorizationView>
) {

	fun create(pluginDescriptor: OneFeedPluginDescriptor): PluginAuthorizationViewModel {
		return PluginAuthorizationViewModel(
				pluginDescriptor,
				pluginStore = pluginStoreProvider.get(),
				view = pluginAuthorizationViewProvider.get()
		)
	}
}
