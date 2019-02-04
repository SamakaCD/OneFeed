package com.ivansadovyi.domain.plugin.auth

interface PluginAuthorizationRepository {

	suspend fun clear()

	suspend fun getPluginAuthorizations(): List<PluginAuthorization>

	suspend fun putPluginAuthorization(pluginAuthorization: PluginAuthorization)
}
