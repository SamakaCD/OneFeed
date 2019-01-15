package com.ivansadovyi.domain.plugin.descriptor

import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorActions.FetchDescriptorsSucceeded
import com.ivansadovyi.domain.utils.truba.StoreMiddleware
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginDescriptorStoreImpl @Inject constructor() : StoreMiddleware(), PluginDescriptorStore {

	override var pluginDescriptors = emptyList<OneFeedPluginDescriptor>()

	override fun onAction(action: Any): Boolean {
		when (action) {
			is FetchDescriptorsSucceeded -> {
				pluginDescriptors = action.descriptors
				return true
			}
		}

		return false
	}
}