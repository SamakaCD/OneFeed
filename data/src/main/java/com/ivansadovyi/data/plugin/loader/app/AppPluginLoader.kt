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
		return instantiate(pluginDescriptor.applicationInfo, pluginDescriptor.className)
	}

	override suspend fun instantiate(pluginClassName: String): OneFeedPlugin {
		for (descriptor in descriptorsCache) {
			if (descriptor.className == pluginClassName) {
				return instantiate(descriptor)
			}
		}

		throw IllegalArgumentException("Can not instantiate plugin with class name ($pluginClassName). " +
				"No OneFeed plugin application found")
	}

	private suspend fun refreshDescriptorsCache() = withContext(Dispatchers.IO) {
		descriptorsCache = context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
				.asSequence()
				.filter { it.hasOneFeedPlugin }
				.map { descriptorFactory.create(it) }
				.toList()
	}

	private fun instantiate(applicationInfo: ApplicationInfo, pluginClassName: String): OneFeedPlugin {
		val apkPath = applicationInfo.sourceDir
		val dexOptimizedPath = context.getDir(OPTIMIZED_DEX_DIR_NAME, 0).path
		val classLoader = DexClassLoader(apkPath, dexOptimizedPath, null, javaClass.classLoader)
		val pluginClass = classLoader.loadClass(pluginClassName)
		return pluginClass.newInstance() as OneFeedPlugin
	}

	private val ApplicationInfo.hasOneFeedPlugin
		get() = metaData?.getString(META_PLUGIN_CLASS_NAME) != null

	companion object {
		const val META_PLUGIN_CLASS_NAME = "oneFeedPluginClassName"
		private const val OPTIMIZED_DEX_DIR_NAME = "dex"
	}
}