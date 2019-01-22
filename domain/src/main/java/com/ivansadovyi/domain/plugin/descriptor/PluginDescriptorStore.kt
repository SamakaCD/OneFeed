package com.ivansadovyi.domain.plugin.descriptor

import com.ivansadovyi.domain.Store
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.OneFeedPluginParams
import io.reactivex.Single

interface PluginDescriptorStore : Store<PluginDescriptorStore> {

	val pluginDescriptors: List<OneFeedPluginDescriptor>

	fun instantiatePlugin(pluginDescriptor: OneFeedPluginDescriptor, params: OneFeedPluginParams): Single<OneFeedPlugin>
}