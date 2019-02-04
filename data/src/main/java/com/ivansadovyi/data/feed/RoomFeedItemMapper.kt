package com.ivansadovyi.data.feed

import com.ivansadovyi.sdk.FeedItem

object RoomFeedItemMapper {

	fun fromRoom(input: RoomFeedItem): FeedItem {
		return with(input) {
			FeedItem(id, title, content, publicationDate, avatarImageUrl)
		}
	}

	fun toRoom(input: FeedItem): RoomFeedItem {
		return with(input) {
			RoomFeedItem(id, title, content, publicationDate, avatarImageUrl)
		}
	}
}
