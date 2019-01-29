package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.Store
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.AuthorizationParams

interface PluginStore : Store<PluginStore> {

	val plugins: List<OneFeedPlugin>

	fun getAuthorizingPluginByDescriptor(descriptor: OneFeedPluginDescriptor): OneFeedPlugin

	fun getCachedAuthorizationParams(plugin: OneFeedPlugin): AuthorizationParams

	fun putAuthorizationParamsIntoCache(plugin: OneFeedPlugin, authParams: AuthorizationParams)

	fun putPlugin(plugin: OneFeedPlugin)
}
