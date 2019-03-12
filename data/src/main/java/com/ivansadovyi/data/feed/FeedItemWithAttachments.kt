package com.ivansadovyi.data.feed

import androidx.room.Embedded
import androidx.room.Relation
import com.ivansadovyi.domain.feed.BundledFeedItem

class FeedItemWithAttachments {

	@Embedded
	lateinit var item: RoomFeedItem

	@Relation(parentColumn = "id", entityColumn = "itemId")
	lateinit var images: List<RoomFeedImage>

	@Relation(parentColumn = "id", entityColumn = "feedItemId")
	lateinit var subItems: List<RoomSubItem>

	constructor()

	constructor(bundledFeedItem: BundledFeedItem) {
		item = RoomFeedItemMapper.toRoom(bundledFeedItem)
		images = bundledFeedItem.images.map { image ->
			RoomFeedImageMapper.toRoom(image, bundledFeedItem.id)
		}
		subItems = bundledFeedItem.subItems.map { subItem ->
			RoomSubItemMapper.toRoom(subItem, bundledFeedItem.id)
		}
	}
}
