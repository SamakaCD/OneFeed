package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.utils.ObservableStore
import com.ivansadovyi.domain.utils.ObservableValue
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.AuthorizationParams
import com.ivansadovyi.sdk.auth.AuthorizationState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginStoreImpl @Inject constructor() : ObservableStore<PluginStore>(), PluginStore {

	override var plugins: MutableList<OneFeedPlugin> by ObservableValue(defaultValue = mutableListOf())

	private val authorizationParamsCache = mutableMapOf<OneFeedPlugin, AuthorizationParams>()

	override fun getAuthorizingPluginByDescriptor(descriptor: OneFeedPluginDescriptor): OneFeedPlugin {
		val instance = plugins.asSequence()
				.filter { it.authorizationState == AuthorizationState.AUTHORIZING }
				.filter { it.descriptor == descriptor }
				.firstOrNull()

		return instance
				?: throw IllegalStateException("Cannot find authorizing plugin instance with descriptor [$descriptor]")
	}

	override fun getCachedAuthorizationParams(plugin: OneFeedPlugin): AuthorizationParams {
		return authorizationParamsCache[plugin]
				?: throw IllegalArgumentException("Cannot find plugin with descriptor [${plugin.descriptor}] in cache")
	}

	override fun putAuthorizationParamsIntoCache(plugin: OneFeedPlugin, authParams: AuthorizationParams) {
		authorizationParamsCache[plugin] = authParams
	}

	override fun putPlugin(plugin: OneFeedPlugin) {
		plugins.add(plugin)
		notifyChange()
	}
}
