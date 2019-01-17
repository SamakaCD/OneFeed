package com.ivansadovyi.data.plugin.app

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.ivansadovyi.data.plugin.app.OneFeedAppPluginDescriptor.Companion.META_CLASS_NAME
import com.ivansadovyi.domain.plugin.descriptor.OneFeedPluginDescriptorProcessingException

class OneFeedAppPluginDescriptorMapper(private val packageManager: PackageManager) {

	fun toDescriptor(applicationInfo: ApplicationInfo): OneFeedAppPluginDescriptor {
		return OneFeedAppPluginDescriptor(
				name = applicationInfo.loadLabel(packageManager).toString(),
				className = getPluginClassName(applicationInfo),
				iconUri = "",
				applicationInfo = applicationInfo
		)
	}

	private fun getPluginClassName(applicationInfo: ApplicationInfo): String {
		return applicationInfo.metaData.getString(META_CLASS_NAME)
				?: throw OneFeedPluginDescriptorProcessingException("Cannot find meta data with " +
						"plugin class name in package ${applicationInfo.packageName}")
	}
}
