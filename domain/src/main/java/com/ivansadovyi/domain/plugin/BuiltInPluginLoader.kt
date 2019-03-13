package com.ivansadovyi.domain.plugin

import com.ivansadovyi.sdk.OneFeedPluginDescriptor

interface BuiltInPluginLoader : PluginLoader {

	suspend fun getDefaultBuiltInPluginDescriptors(): List<OneFeedPluginDescriptor>
}
