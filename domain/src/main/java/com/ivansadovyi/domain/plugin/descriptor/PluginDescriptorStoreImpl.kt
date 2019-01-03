package com.ivansadovyi.domain.plugin.descriptor

import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.domain.utils.ObservableStore
import com.ivansadovyi.domain.utils.ObservableValue
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginDescriptorStoreImpl @Inject constructor(
		private val pluginLoader: PluginLoader
) : ObservableStore<PluginDescriptorStore>(), PluginDescriptorStore {

	override var pluginDescriptors: List<OneFeedPluginDescriptor> by ObservableValue(defaultValue = emptyList())

	init {
		pluginLoader.getDescriptors().subscribeBy {
			pluginDescriptors = it
		}
	}

	override fun instantiatePlugin(pluginDescriptor: OneFeedPluginDescriptor): Single<OneFeedPlugin> {
		return pluginLoader.instantiate(pluginDescriptor).subscribeOn(Schedulers.computation())
	}
}