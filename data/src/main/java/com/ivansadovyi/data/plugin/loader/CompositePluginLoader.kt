package com.ivansadovyi.data.plugin.loader

import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor

class CompositePluginLoader(private vararg val loaders: PluginLoader) : PluginLoader {

	override suspend fun getDescriptors(): List<OneFeedPluginDescriptor> {
		return loaders.flatMap { it.getDescriptors() }
	}

	override suspend fun getDescriptorByClassName(pluginClassName: String): OneFeedPluginDescriptor {
		if (loaders.isEmpty()) {
			throw IllegalStateException("Can not find any loader which has plugin descriptor with " +
					"class name ($pluginClassName) because there is no any PluginLoader registered")
		}

		for (loader in loaders) {
			if (loader.canInstantiatePlugin(pluginClassName)) {
				return loader.getDescriptorByClassName(pluginClassName)
			}
		}
		throw IllegalStateException("Can not find any loader which has plugin descriptor with class name ($pluginClassName)")
	}

	override suspend fun canInstantiatePlugin(pluginDescriptor: OneFeedPluginDescriptor): Boolean {
		return loaders.any { it.canInstantiatePlugin(pluginDescriptor) }
	}

	override suspend fun canInstantiatePlugin(pluginClassName: String): Boolean {
		return loaders.any { it.canInstantiatePlugin(pluginClassName) }
	}

	override suspend fun instantiate(pluginDescriptor: OneFeedPluginDescriptor): OneFeedPlugin {
		if (loaders.isEmpty()) {
			throw IllegalStateException("Can not instantiate plugin with descriptor [$pluginDescriptor] " +
					"because there is no any PluginLoader registered")
		}

		for (loader in loaders) {
			if (loader.canInstantiatePlugin(pluginDescriptor)) {
				return loader.instantiate(pluginDescriptor)
			}
		}

		throw IllegalStateException("Can not instantiate plugin with descriptor [$pluginDescriptor] " +
				"because there is no any PluginLoader which can do it")
	}
}
