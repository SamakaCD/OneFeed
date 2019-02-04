package com.ivansadovyi.data.plugin.authorization

import com.ivansadovyi.domain.plugin.auth.PluginAuthorization

object RoomPluginAuthorizationMapper {

	fun fromRoom(input: RoomPluginAuthorization): PluginAuthorization {
		return with(input) {
			PluginAuthorization(authorization, pluginClassName)
		}
	}

	fun toRoom(input: PluginAuthorization): RoomPluginAuthorization {
		return with(input) {
			RoomPluginAuthorization(authorization, pluginClassName)
		}
	}
}
