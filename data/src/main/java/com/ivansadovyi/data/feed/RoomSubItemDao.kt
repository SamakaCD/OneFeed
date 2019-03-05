package com.ivansadovyi.data.feed

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class RoomSubItemDao {

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	abstract fun insertListItems(items: List<RoomSubItem>)

	@Query("DELETE FROM subItems")
	abstract fun deleteAll()
}
