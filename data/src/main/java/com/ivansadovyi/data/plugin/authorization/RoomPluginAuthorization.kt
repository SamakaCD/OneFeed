package com.ivansadovyi.data.plugin.authorization

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pluginAuthorizations")
class RoomPluginAuthorization(
		var authorization: String,
		var pluginClassName: String
) {

	@PrimaryKey(autoGenerate = true)
	var id: Int = 0
}
