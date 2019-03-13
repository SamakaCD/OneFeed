package com.ivansadovyi.domain.plugin.descriptor.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorStore

class LoadPluginDescriptorsUseCase(
		private val pluginDescriptorStore: PluginDescriptorStore,
		private val pluginLoader: PluginLoader
) : UseCase<Unit> {

	override suspend fun execute() {
		val descriptors = pluginLoader.getDescriptors()
		pluginDescriptorStore.setLoadedPluginDescriptors(descriptors)
	}
}
