package com.ivansadovyi.data.plugin.loader.app

import android.content.pm.ApplicationInfo
import com.ivansadovyi.sdk.OneFeedPluginDescriptor

class OneFeedAppPluginDescriptor(
		name: String,
		className: String,
		iconUri: String,
		val applicationInfo: ApplicationInfo
) : OneFeedPluginDescriptor(name, className, iconUri)
