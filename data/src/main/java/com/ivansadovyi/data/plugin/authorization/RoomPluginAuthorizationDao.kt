package com.ivansadovyi.data.plugin.authorization

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
abstract class RoomPluginAuthorizationDao {

	@Insert
	abstract fun insert(authorization: RoomPluginAuthorization)

	@Query("SELECT * FROM pluginAuthorizations")
	abstract fun getAll(): List<RoomPluginAuthorization>

	@Query("DELETE FROM pluginAuthorizations")
	abstract fun deleteAll()
}
