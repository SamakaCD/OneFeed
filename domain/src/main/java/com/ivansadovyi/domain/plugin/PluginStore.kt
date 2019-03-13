package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.plugin.PluginAction.ClearPluginInstancesAction
import com.ivansadovyi.domain.plugin.PluginAction.NewPluginInstanceAction
import com.ivansadovyi.domain.utils.truba.Action
import com.ivansadovyi.domain.utils.truba.Store
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.AuthorizationParams
import com.ivansadovyi.sdk.auth.AuthorizationState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginStore @Inject constructor() : Store<Action>() {

	var plugins: MutableList<OneFeedPlugin> = mutableListOf()

	private val authorizationParamsCache = mutableMapOf<OneFeedPlugin, AuthorizationParams>()

	fun clear() {
		plugins.clear()
		notifyChange(ClearPluginInstancesAction)
	}

	fun getAuthorizedPlugins(): List<OneFeedPlugin> {
		return plugins.filter { it.authorizationState == AuthorizationState.AUTHORIZED }
	}

	fun getAuthorizingPluginByDescriptor(descriptor: OneFeedPluginDescriptor): OneFeedPlugin {
		val instance = plugins.asSequence()
				.filter { it.authorizationState == AuthorizationState.AUTHORIZING }
				.filter { it.descriptor == descriptor }
				.firstOrNull()

		return instance
				?: throw IllegalStateException("Cannot find authorizing plugin instance with descriptor [$descriptor]")
	}

	fun getCachedAuthorizationParams(plugin: OneFeedPlugin): AuthorizationParams {
		return authorizationParamsCache[plugin]
				?: throw IllegalArgumentException("Cannot find plugin with descriptor [${plugin.descriptor}] in cache")
	}

	fun putAuthorizationParamsIntoCache(plugin: OneFeedPlugin, authParams: AuthorizationParams) {
		authorizationParamsCache[plugin] = authParams
	}

	fun putPlugin(plugin: OneFeedPlugin) {
		plugins.add(plugin)
		notifyChange(NewPluginInstanceAction(plugin))
	}
}
