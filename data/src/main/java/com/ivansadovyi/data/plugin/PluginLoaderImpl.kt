package com.ivansadovyi.data.plugin

import android.app.Application
import android.content.pm.PackageManager
import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginLoaderImpl @Inject constructor(private val application: Application) : PluginLoader {

	override fun getDescriptors(): Single<List<OneFeedPluginDescriptor>> {
		val packageManager = application.packageManager
		return Single.just(packageManager.getInstalledApplications(PackageManager.GET_META_DATA))
				.flatMapObservable { Observable.fromIterable(it) }
				.map {
					OneFeedPluginDescriptor.Builder()
							.setName(it.name)
							.setClassName(it.packageName)
							.setIconUri("")
							.build()
				}
				.toList()
	}

	override fun instantiate(pluginDescriptor: OneFeedPluginDescriptor): Single<OneFeedPlugin> {
		throw IllegalArgumentException("Cannot instantiate plugin with class name ${pluginDescriptor.className}")
	}
}
