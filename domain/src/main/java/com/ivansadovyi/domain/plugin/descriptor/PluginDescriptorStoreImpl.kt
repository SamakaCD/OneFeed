package com.ivansadovyi.domain.plugin.descriptor

import com.ivansadovyi.domain.utils.ObservableStore
import com.ivansadovyi.domain.utils.ObservableValue
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginDescriptorStoreImpl @Inject constructor(
		private val interactor: PluginDescriptorInteractor
) : ObservableStore<PluginDescriptorStore>(), PluginDescriptorStore {

	override var pluginDescriptors: List<OneFeedPluginDescriptor> by ObservableValue(defaultValue = emptyList())

	private val coroutineScope = CoroutineScope(Dispatchers.Default + Job())

	init {
		coroutineScope.launch {
			pluginDescriptors = interactor.getPluginDescriptors()
		}
	}
}