package com.ivansadovyi.domain.plugin.descriptor.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.sdk.OneFeedPluginDescriptor

class GetPluginDescriptorsUseCase(private val pluginLoader: PluginLoader) : UseCase<List<OneFeedPluginDescriptor>> {

	override suspend fun execute(): List<OneFeedPluginDescriptor> {
		return pluginLoader.getDescriptors()
	}
}
