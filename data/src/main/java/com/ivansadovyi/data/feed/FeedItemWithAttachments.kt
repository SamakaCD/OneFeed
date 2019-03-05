package com.ivansadovyi.data.feed

import androidx.room.Embedded
import androidx.room.Relation

class FeedItemWithAttachments {

	@Embedded
	lateinit var item: RoomFeedItem

	@Relation(parentColumn = "id", entityColumn = "itemId")
	lateinit var images: List<RoomFeedImage>

	@Relation(parentColumn = "id", entityColumn = "feedItemId")
	lateinit var subItems: List<RoomSubItem>
}
