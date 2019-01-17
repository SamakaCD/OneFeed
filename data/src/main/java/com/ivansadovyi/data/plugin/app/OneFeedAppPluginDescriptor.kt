package com.ivansadovyi.data.plugin.app

import android.content.pm.ApplicationInfo
import com.ivansadovyi.sdk.OneFeedPluginDescriptor

class OneFeedAppPluginDescriptor(
		name: String,
		className: String,
		iconUri: String,
		val applicationInfo: ApplicationInfo
) : OneFeedPluginDescriptor(name, className, iconUri) {

	companion object {
		const val META_CLASS_NAME = "oneFeedPluginClassName"
	}
}
