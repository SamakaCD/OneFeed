package com.ivansadovyi.domain.plugin.descriptor

import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.domain.plugin.descriptor.usecase.GetPluginDescriptorsUseCase
import com.ivansadovyi.domain.plugin.descriptor.usecase.InstantiatePluginUseCase
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.OneFeedPluginParams
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class PluginDescriptorInteractorImpl @Inject constructor(
		private val pluginLoaderProvider: Provider<PluginLoader>
) : PluginDescriptorInteractor {

	override suspend fun getPluginDescriptors(): List<OneFeedPluginDescriptor> {
		return GetPluginDescriptorsUseCase(
				pluginLoader = pluginLoaderProvider.get()
		).execute()
	}

	override suspend fun instantiate(pluginDescriptor: OneFeedPluginDescriptor, initParams: OneFeedPluginParams): OneFeedPlugin {
		return InstantiatePluginUseCase(
				pluginDescriptor = pluginDescriptor,
				initParams = initParams,
				pluginLoader = pluginLoaderProvider.get()
		).execute()
	}
}
