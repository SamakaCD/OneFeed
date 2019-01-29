package com.ivansadovyi.domain.plugin.auth

import io.reactivex.Single

interface PluginAuthorizationsDao {

	fun getPluginAuthorizations(): Single<List<PluginAuthorization>>

	fun putPluginAuthorization(pluginAuthorization: PluginAuthorization)
}
