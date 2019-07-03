package com.ivansadovyi.data.feed

import androidx.room.*

@Dao
abstract class RoomFeedItemDao {

	@Transaction
	@Query("SELECT * FROM feedItems ORDER BY publicationDate DESC")
	abstract fun getAll(): List<FeedItemWithAttachments>

	@Transaction
	open fun deleteAllAndInsert(itemsWithAttachments: List<FeedItemWithAttachments>) {
		deleteAll()
		insert(itemsWithAttachments)
	}

	@Transaction
	open fun insert(itemsWithAttachments: List<FeedItemWithAttachments>) {
		itemsWithAttachments.forEach(::insert)
	}

	@Transaction
	open fun insert(itemWithAttachments: FeedItemWithAttachments) {
		insert(itemWithAttachments.item)
		itemWithAttachments.images.forEach(::insertImage)
		itemWithAttachments.subItems.forEach(::insertSubItem)
	}

	@Update
	abstract fun update(item: RoomFeedItem)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun insert(item: RoomFeedItem)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun insertImage(image: RoomFeedImage)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun insertSubItem(subItem: RoomSubItem)

	@Query("DELETE FROM feedItems")
	abstract fun deleteAll()
}
