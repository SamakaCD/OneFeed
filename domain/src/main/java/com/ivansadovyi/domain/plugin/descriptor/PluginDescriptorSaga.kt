package com.ivansadovyi.domain.plugin.descriptor

import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorActions.FetchDescriptors
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorActions.FetchDescriptorsSucceeded
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorActions.InstantiatePluginActions.*
import com.ivansadovyi.domain.utils.await
import com.ivansadovyi.truba.ActionDispatcher
import com.ivansadovyi.truba.saga.Saga
import com.ivansadovyi.truba.saga.actionBinding
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginDescriptorSaga @Inject constructor(
		private val pluginLoader: PluginLoader,
		dispatcher: ActionDispatcher<Any>
) : Saga<Any>(dispatcher) {

	private val fetchDescriptors = actionBinding<FetchDescriptors> {
		val descriptors = pluginLoader.getDescriptors().await(subscribeOn = Schedulers.io())
		dispatch(FetchDescriptorsSucceeded(descriptors))
	}

	private val instantiatePlugin = actionBinding<InstantiatePlugin> {
		try {
			val plugin = pluginLoader.instantiate(pluginDescriptor).await(subscribeOn = Schedulers.computation())
			plugin.onInit(params)
			dispatch(InstantiatePluginSucceeded(pluginDescriptor, plugin))
		} catch (cause: Throwable) {
			val throwable = RuntimeException("Cannot instantiate plugin with descriptor [${pluginDescriptor}]", cause)
			dispatch(InstantiatePluginFailed(pluginDescriptor, throwable))
		}
	}

	init {
		bindAction(fetchDescriptors)
		bindAction(instantiatePlugin)
	}
}
