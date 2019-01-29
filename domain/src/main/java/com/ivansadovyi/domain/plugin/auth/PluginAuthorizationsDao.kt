package com.ivansadovyi.domain.plugin.auth

interface PluginAuthorizationsDao {

	suspend fun getPluginAuthorizations(): List<PluginAuthorization>

	suspend fun putPluginAuthorization(pluginAuthorization: PluginAuthorization)
}
