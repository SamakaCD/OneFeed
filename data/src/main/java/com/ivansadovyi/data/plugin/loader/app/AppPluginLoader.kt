package com.ivansadovyi.data.plugin.loader.app

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import dalvik.system.DexClassLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppPluginLoader(private val context: Context) : PluginLoader {

	private val descriptorFactory = OneFeedAppPluginDescriptorFactory(context)
	private var descriptorsCache = emptyList<OneFeedPluginDescriptor>()

	override suspend fun getDescriptors(): List<OneFeedPluginDescriptor> {
		refreshDescriptorsCache()
		return descriptorsCache
	}

	override suspend fun getDescriptorByClassName(pluginClassName: String): OneFeedPluginDescriptor {
		if (descriptorsCache.isEmpty()) {
			refreshDescriptorsCache()
		}

		for (descriptor in descriptorsCache) {
			if (descriptor.className == pluginClassName) {
				return descriptor
			}
		}

		throw IllegalArgumentException("Can not find plugin descriptor with class name $pluginClassName")
	}

	override suspend fun canInstantiatePlugin(pluginDescriptor: OneFeedPluginDescriptor): Boolean {
		return pluginDescriptor is OneFeedAppPluginDescriptor || canInstantiatePlugin(pluginDescriptor.className)
	}

	override suspend fun canInstantiatePlugin(pluginClassName: String): Boolean {
		if (descriptorsCache.isEmpty()) {
			refreshDescriptorsCache()
		}

		return descriptorsCache.any { it.className == pluginClassName }
	}

	override suspend fun instantiate(pluginDescriptor: OneFeedPluginDescriptor): OneFeedPlugin {
		val pluginDescriptor = pluginDescriptor as OneFeedAppPluginDescriptor
		val apkPath = pluginDescriptor.applicationInfo.sourceDir
		val dexOptimizedPath = context.getDir(OPTIMIZED_DEX_DIR_NAME, 0).path
		val classLoader = DexClassLoader(apkPath, dexOptimizedPath, null, javaClass.classLoader)
		val pluginClass = classLoader.loadClass(pluginDescriptor.className)
		return pluginClass.newInstance() as OneFeedPlugin
	}

	private suspend fun refreshDescriptorsCache() = withContext(Dispatchers.IO) {
		descriptorsCache = context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
				.asSequence()
				.filter { it.hasOneFeedPlugin }
				.map { descriptorFactory.create(it) }
				.toList()
	}

	private val ApplicationInfo.hasOneFeedPlugin
		get() = metaData?.getString(META_PLUGIN_CLASS_NAME) != null

	companion object {
		const val META_PLUGIN_CLASS_NAME = "oneFeedPluginClassName"
		private const val OPTIMIZED_DEX_DIR_NAME = "dex"
	}
}