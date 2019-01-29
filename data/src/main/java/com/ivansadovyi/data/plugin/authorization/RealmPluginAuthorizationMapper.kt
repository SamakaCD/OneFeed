package com.ivansadovyi.data.plugin.authorization

import com.ivansadovyi.domain.plugin.auth.PluginAuthorization

object RealmPluginAuthorizationMapper {

	fun fromRealm(input: RealmPluginAuthorization): PluginAuthorization {
		with (input) {
			return PluginAuthorization(authorization, pluginClassName)
		}
	}

	fun toRealm(input: PluginAuthorization): RealmPluginAuthorization {
		with (input) {
			return RealmPluginAuthorization(authorization, pluginClassName)
		}
	}
}
