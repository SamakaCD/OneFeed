package com.ivansadovyi.domain.plugin.descriptor

import com.ivansadovyi.domain.Store
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import io.reactivex.Single

interface PluginDescriptorStore : Store<PluginDescriptorStore> {

	val pluginDescriptors: List<OneFeedPluginDescriptor>

	fun instantiatePlugin(pluginDescriptor: OneFeedPluginDescriptor): Single<OneFeedPlugin>
}