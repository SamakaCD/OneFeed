package com.ivansadovyi.domain.plugin.descriptor

import com.ivansadovyi.domain.Store
import com.ivansadovyi.sdk.OneFeedPluginDescriptor

interface PluginDescriptorStore : Store<Any> {

	val pluginDescriptors: List<OneFeedPluginDescriptor>

}