package com.ivansadovyi.domain.plugin

import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor

interface PluginLoader {

	suspend fun getDescriptors(): List<OneFeedPluginDescriptor>

	suspend fun getDescriptorByClassName(pluginClassName: String): OneFeedPluginDescriptor

	suspend fun canInstantiatePlugin(pluginDescriptor: OneFeedPluginDescriptor): Boolean

	suspend fun canInstantiatePlugin(pluginClassName: String): Boolean

	suspend fun instantiate(pluginDescriptor: OneFeedPluginDescriptor): OneFeedPlugin
}
