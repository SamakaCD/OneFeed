package com.ivansadovyi.data.plugin.loader.app

import android.content.Context
import android.content.pm.ApplicationInfo

class OneFeedAppPluginDescriptorMapper(context: Context) {

	private val packageManager = context.packageManager

	fun toDescriptor(applicationInfo: ApplicationInfo): OneFeedAppPluginDescriptor {
		return OneFeedAppPluginDescriptor(
				name = applicationInfo.loadLabel(packageManager).toString(),
				className = applicationInfo.getPluginClassName(),
				iconUri = "", // TODO
				applicationInfo = applicationInfo
		)
	}

	private fun ApplicationInfo.getPluginClassName(): String {
		return metaData?.getString(AppPluginLoader.META_PLUGIN_CLASS_NAME)
				?: throw IllegalArgumentException("Package ($packageName) does not contain " +
						"meta data with plugin class name")
	}
}
