package com.ivansadovyi.data.feed

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable

@Dao
abstract class RoomFeedItemDao {

	@Query("SELECT * FROM feedItems ORDER BY publicationDate DESC")
	abstract fun getAll(): Observable<List<FeedItemWithAttachments>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun insert(item: RoomFeedItem)

	@Query("DELETE FROM feedItems")
	abstract fun deleteAll()
}
