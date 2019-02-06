package com.ivansadovyi.domain.plugin.usecase

import com.ivansadovyi.domain.plugin.PluginIconCache
import com.ivansadovyi.sdk.OneFeedPlugin

class CachePluginIconUseCase(
		private val plugin: OneFeedPlugin,
		private val pluginIconCache: PluginIconCache
) : PluginInvocationUseCase<Unit>(plugin) {

	override suspend fun executePluginInvocation() {
		pluginIconCache.put(plugin.descriptor.className, plugin.icon)
	}
}
