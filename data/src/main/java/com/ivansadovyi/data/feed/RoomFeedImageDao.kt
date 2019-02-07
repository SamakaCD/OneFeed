package com.ivansadovyi.data.feed

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class RoomFeedImageDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun insertImages(images: List<RoomFeedImage>)

	@Query("DELETE FROM feedItems")
	abstract fun deleteAll()
}
