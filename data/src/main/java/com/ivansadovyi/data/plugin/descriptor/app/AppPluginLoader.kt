package com.ivansadovyi.data.plugin.descriptor.app

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import dalvik.system.DexClassLoader
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class AppPluginLoader(private val context: Context) : PluginLoader {

	private val mapper = OneFeedAppPluginDescriptorMapper(context)

	override fun getDescriptors(): Single<List<OneFeedPluginDescriptor>> {
		val packageManager = context.packageManager
		return Observable.fromIterable(packageManager.getInstalledApplications(PackageManager.GET_META_DATA))
				.filter { it.hasOneFeedPlugin }
				.map { mapper.toDescriptor(it) }
				.map { it as OneFeedPluginDescriptor }
				.toList()
	}

	override fun canInstantiatePlugin(pluginDescriptor: OneFeedPluginDescriptor): Single<Boolean> {
		return Single.just(pluginDescriptor is OneFeedAppPluginDescriptor)
	}

	override fun instantiate(pluginDescriptor: OneFeedPluginDescriptor): Single<OneFeedPlugin> {
		return Single.fromCallable<OneFeedPlugin> {
			val pluginDescriptor = pluginDescriptor as OneFeedAppPluginDescriptor
			val apkPath = pluginDescriptor.applicationInfo.sourceDir
			val dexOptimizedPath = context.getDir(OPTIMIZED_DEX_DIR_NAME, 0).path
			val classLoader = DexClassLoader(apkPath, dexOptimizedPath, null, javaClass.classLoader)
			val pluginClass = classLoader.loadClass(pluginDescriptor.className)
			return@fromCallable pluginClass.newInstance() as OneFeedPlugin
		}.subscribeOn(Schedulers.computation())
	}

	private val ApplicationInfo.hasOneFeedPlugin
		get() = metaData?.getString(META_PLUGIN_CLASS_NAME) != null

	companion object {
		const val META_PLUGIN_CLASS_NAME = "oneFeedPluginClassName"
		private const val OPTIMIZED_DEX_DIR_NAME = "dex"
	}
}