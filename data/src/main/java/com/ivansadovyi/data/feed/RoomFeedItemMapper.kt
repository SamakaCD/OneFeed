package com.ivansadovyi.data.feed

import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.toBundledItem
import com.ivansadovyi.sdk.FeedItem

object RoomFeedItemMapper {

	fun fromRoom(input: RoomFeedItem): BundledFeedItem {
		return FeedItem.Builder()
				.setId(input.id)
				.setTitle(input.title)
				.setContent(input.content)
				.setPublicationDate(input.publicationDate)
				.setAvatarImageUrl(input.avatarImageUrl)
				.build()
				.toBundledItem(pluginClassName = input.pluginClassName)
	}

	fun toRoom(input: BundledFeedItem): RoomFeedItem {
		return with(input) {
			RoomFeedItem(id, title, content, publicationDate, avatarImageUrl, pluginClassName)
		}
	}
}
