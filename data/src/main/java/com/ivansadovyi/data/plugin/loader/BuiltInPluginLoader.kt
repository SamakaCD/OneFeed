package com.ivansadovyi.data.plugin.loader

import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.onefeed.plugin.twitter.TwitterPlugin
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BuiltInPluginLoader @Inject constructor() : PluginLoader {

	override suspend fun getDescriptors(): List<OneFeedPluginDescriptor> {
		return listOf(TwitterPlugin.DESCRIPTOR)
	}

	override suspend fun getDescriptorByClassName(pluginClassName: String): OneFeedPluginDescriptor {
		return when (pluginClassName) {
			TwitterPlugin.DESCRIPTOR.className -> TwitterPlugin.DESCRIPTOR
			else -> throw IllegalArgumentException("Can not find plugin descriptor with class name ($pluginClassName)")
		}
	}

	override suspend fun canInstantiatePlugin(pluginDescriptor: OneFeedPluginDescriptor): Boolean {
		return canInstantiatePlugin(pluginDescriptor.className)
	}

	override suspend fun canInstantiatePlugin(pluginClassName: String): Boolean {
		return when (pluginClassName) {
			TwitterPlugin.DESCRIPTOR.className -> true
			else -> false
		}
	}

	override suspend fun instantiate(pluginDescriptor: OneFeedPluginDescriptor): OneFeedPlugin {
		return when (pluginDescriptor) {
			TwitterPlugin.DESCRIPTOR -> TwitterPlugin()
			else -> throw IllegalArgumentException("Can not instantiate plugin with descriptor [$pluginDescriptor]")
		}
	}
}
