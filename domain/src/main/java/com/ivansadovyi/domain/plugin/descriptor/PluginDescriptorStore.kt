package com.ivansadovyi.domain.plugin.descriptor

import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorAction.PluginDescriptorsLoadedAction
import com.ivansadovyi.domain.utils.truba.Store
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginDescriptorStore @Inject constructor() : Store<PluginDescriptorAction>() {

	var pluginDescriptors: List<OneFeedPluginDescriptor> = emptyList()

	fun setLoadedPluginDescriptors(pluginDescriptors: List<OneFeedPluginDescriptor>) {
		this.pluginDescriptors = pluginDescriptors
		notifyChange(PluginDescriptorsLoadedAction)
	}
}