package com.ivansadovyi.domain.plugin.descriptor

import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.OneFeedPluginParams

interface PluginDescriptorInteractor {

	suspend fun getPluginDescriptors(): List<OneFeedPluginDescriptor>

	suspend fun instantiate(pluginDescriptor: OneFeedPluginDescriptor, initParams: OneFeedPluginParams): OneFeedPlugin
}
