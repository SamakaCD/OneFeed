package com.ivansadovyi.data.plugin.app

import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.ivansadovyi.data.plugin.app.OneFeedAppPluginDescriptor.Companion.META_CLASS_NAME
import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import io.reactivex.Observable
import io.reactivex.Single

class AppPluginLoader(application: Application) : PluginLoader {

	private val packageManager = application.packageManager
	private val mapper = OneFeedAppPluginDescriptorMapper(packageManager)

	override fun getDescriptors(): Single<List<OneFeedPluginDescriptor>> {
		return Observable.fromIterable(packageManager.getInstalledApplications(PackageManager.GET_META_DATA))
				.filter { it.isOneFeedPlugin }
				.map { mapper.toDescriptor(it) }
				.map { it as OneFeedPluginDescriptor }
				.toList()
	}

	override fun instantiate(pluginDescriptor: OneFeedPluginDescriptor): Single<OneFeedPlugin> {
		TODO()
	}

	private val ApplicationInfo.isOneFeedPlugin: Boolean
		get() = metaData?.getString(META_CLASS_NAME) != null
}
