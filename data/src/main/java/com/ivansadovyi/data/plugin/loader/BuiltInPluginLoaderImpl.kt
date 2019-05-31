package com.ivansadovyi.data.plugin.loader

import com.ivansadovyi.builtinplugins.recommendations.RecommendationsPlugin
import com.ivansadovyi.data.plugin.just.facebook.FacebookPlugin
import com.ivansadovyi.domain.plugin.BuiltInPluginLoader
import com.ivansadovyi.onefeed.plugin.feedly.FeedlyPlugin
import com.ivansadovyi.onefeed.plugin.twitter.TwitterPlugin
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BuiltInPluginLoaderImpl @Inject constructor() : BuiltInPluginLoader {

	override suspend fun getDescriptors(): List<OneFeedPluginDescriptor> {
		return ALL_DESCRIPTORS
	}

	override suspend fun getDefaultBuiltInPluginDescriptors(): List<OneFeedPluginDescriptor> {
		return DEFAULT_DESCRIPTORS
	}

	override suspend fun getDescriptorByClassName(pluginClassName: String): OneFeedPluginDescriptor {
		return ALL_DESCRIPTORS.find { it.className == pluginClassName }
				?: throw IllegalArgumentException("Can not find plugin descriptor with class name ($pluginClassName)")
	}

	override suspend fun canInstantiatePlugin(pluginDescriptor: OneFeedPluginDescriptor): Boolean {
		return canInstantiatePlugin(pluginDescriptor.className)
	}

	override suspend fun canInstantiatePlugin(pluginClassName: String): Boolean {
		return ALL_DESCRIPTORS.any { it.className == pluginClassName }
	}

	override suspend fun instantiate(pluginDescriptor: OneFeedPluginDescriptor): OneFeedPlugin {
		return when (pluginDescriptor) {
			TwitterPlugin.DESCRIPTOR -> TwitterPlugin()
			FeedlyPlugin.DESCRIPTOR -> FeedlyPlugin()
			RecommendationsPlugin.DESCRIPTOR -> RecommendationsPlugin()
			FacebookPlugin.DESCRIPTOR -> FacebookPlugin()
			else -> throw IllegalArgumentException("Can not instantiate plugin with descriptor [$pluginDescriptor]")
		}
	}

	companion object {
		private val DEFAULT_DESCRIPTORS = listOf(
				RecommendationsPlugin.DESCRIPTOR,
				FacebookPlugin.DESCRIPTOR
		)
		private val AUTHORIZING_DESCRIPTORS = listOf(TwitterPlugin.DESCRIPTOR)
		private val ALL_DESCRIPTORS = DEFAULT_DESCRIPTORS + AUTHORIZING_DESCRIPTORS
	}
}
