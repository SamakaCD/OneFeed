package com.ivansadovyi.data.plugin.loader

import com.ivansadovyi.builtinplugins.recommendations.RecommendationsPlugin
import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.onefeed.plugin.twitter.TwitterPlugin
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BuiltInPluginLoader @Inject constructor() : PluginLoader {

	override suspend fun getDescriptors(): List<OneFeedPluginDescriptor> {
		return DESCRIPTORS
	}

	override suspend fun getDescriptorByClassName(pluginClassName: String): OneFeedPluginDescriptor {
		return DESCRIPTORS.find { it.className == pluginClassName }
				?: throw IllegalArgumentException("Can not find plugin descriptor with class name ($pluginClassName)")
	}

	override suspend fun canInstantiatePlugin(pluginDescriptor: OneFeedPluginDescriptor): Boolean {
		return canInstantiatePlugin(pluginDescriptor.className)
	}

	override suspend fun canInstantiatePlugin(pluginClassName: String): Boolean {
		return DESCRIPTORS.any { it.className == pluginClassName }
	}

	override suspend fun instantiate(pluginDescriptor: OneFeedPluginDescriptor): OneFeedPlugin {
		println(pluginDescriptor == RecommendationsPlugin.DESCRIPTOR)
		return when (pluginDescriptor) {
			TwitterPlugin.DESCRIPTOR -> TwitterPlugin()
			RecommendationsPlugin.DESCRIPTOR -> RecommendationsPlugin()
			else -> throw IllegalArgumentException("Can not instantiate plugin with descriptor [$pluginDescriptor]")
		}
	}

	companion object {
		private val DESCRIPTORS = listOf(TwitterPlugin.DESCRIPTOR, RecommendationsPlugin.DESCRIPTOR)
	}
}
