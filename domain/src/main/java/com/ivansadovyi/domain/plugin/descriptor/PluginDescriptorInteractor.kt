package com.ivansadovyi.domain.plugin.descriptor

import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.domain.plugin.descriptor.usecase.InstantiatePluginUseCase
import com.ivansadovyi.domain.plugin.descriptor.usecase.LoadPluginDescriptorsUseCase
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.OneFeedPluginParams
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginDescriptorInteractor @Inject constructor(
		private val pluginDescriptorStore: Lazy<PluginDescriptorStore>,
		private val pluginLoader: Lazy<PluginLoader>
) {

	suspend fun loadPluginDescriptors() {
		LoadPluginDescriptorsUseCase(
				pluginDescriptorStore = pluginDescriptorStore.get(),
						pluginLoader = pluginLoader.get()
		).execute()
	}

	suspend fun instantiate(pluginDescriptor: OneFeedPluginDescriptor, initParams: OneFeedPluginParams): OneFeedPlugin {
		return InstantiatePluginUseCase(
				pluginDescriptor = pluginDescriptor,
				initParams = initParams,
				pluginLoader = pluginLoader.get()
		).execute()
	}
}
