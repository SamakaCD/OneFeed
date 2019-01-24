package com.ivansadovyi.data.plugin

import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.domain.utils.filterAsync
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

class CompositePluginLoader(private vararg val loaders: PluginLoader) : PluginLoader {

	override fun getDescriptors(): Single<List<OneFeedPluginDescriptor>> {
		return Observable.fromArray(*loaders)
				.flatMapSingle { it.getDescriptors() }
				.flatMapIterable { it }
				.toList()
	}

	override fun canInstantiatePlugin(pluginDescriptor: OneFeedPluginDescriptor): Single<Boolean> {
		return Observable.fromArray(*loaders)
				.flatMapSingle { it.canInstantiatePlugin(pluginDescriptor) }
				.any { it }
	}

	override fun instantiate(pluginDescriptor: OneFeedPluginDescriptor): Single<OneFeedPlugin> {
		if (loaders.isEmpty()) {
			return Single.error(IllegalStateException("Can not instantiate plugin with descriptor [$pluginDescriptor] " +
					"because there is no any PluginLoader registered"))
		}

		return Observable.fromArray(*loaders)
				.filterAsync { it.canInstantiatePlugin(pluginDescriptor) }
				.firstOrError()
				.onErrorResumeNext {
					if (it is NoSuchElementException) {
						Single.error(IllegalStateException("Can not instantiate plugin with descriptor [$pluginDescriptor] " +
								"because there is no any PluginLoader which can do it"))
					} else {
						Single.error(it)
					}
				}
				.flatMap { it.instantiate(pluginDescriptor) }
	}
}
