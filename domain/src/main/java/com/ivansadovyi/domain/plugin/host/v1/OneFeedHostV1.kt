package com.ivansadovyi.domain.plugin.host.v1

import com.ivansadovyi.sdk.OneFeedHost
import com.ivansadovyi.sdk.PluginManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OneFeedHostV1 @Inject constructor(private val pluginManager: PluginManagerV1) : OneFeedHost {

	override fun getPluginManager(): PluginManager {
		return pluginManager
	}
}