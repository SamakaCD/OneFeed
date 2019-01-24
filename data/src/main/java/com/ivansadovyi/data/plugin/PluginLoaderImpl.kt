package com.ivansadovyi.data.plugin

import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.onefeed.plugin.twitter.TwitterPlugin
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginLoaderImpl @Inject constructor() : PluginLoader {

	override fun getDescriptors(): Single<List<OneFeedPluginDescriptor>> {
		return Single.just(listOf(TwitterPlugin.DESCRIPTOR))
	}

	override fun canInstantiatePlugin(pluginDescriptor: OneFeedPluginDescriptor): Single<Boolean> {
		return Single.just(true)
	}

	override fun instantiate(pluginDescriptor: OneFeedPluginDescriptor): Single<OneFeedPlugin> {
		return when (pluginDescriptor) {
			TwitterPlugin.DESCRIPTOR -> Single.just(TwitterPlugin())
			else -> throw IllegalArgumentException("Cannot instantiate plugin with class name ${pluginDescriptor.className}")
		}
	}
}
