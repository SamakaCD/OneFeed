package com.ivansadovyi.domain.plugin

import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.OneFeedPlugin
import io.reactivex.Single

interface PluginLoader {

	fun getDescriptors(): Single<List<OneFeedPluginDescriptor>>

	fun instantiate(pluginDescriptor: OneFeedPluginDescriptor): Single<OneFeedPlugin>
}
