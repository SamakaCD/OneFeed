package com.ivansadovyi.domain.plugin.descriptor.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.OneFeedPluginParams

class InstantiatePluginUseCase(
		private val pluginDescriptor: OneFeedPluginDescriptor,
		private val initParams: OneFeedPluginParams,
		private val pluginLoader: PluginLoader
) : UseCase<OneFeedPlugin> {

	override suspend fun execute(): OneFeedPlugin {
		val plugin = pluginLoader.instantiate(pluginDescriptor)
		plugin.onInit(initParams)
		return plugin
	}
}
