package com.ivansadovyi.data.feed

import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.toBundledItem
import com.ivansadovyi.sdk.FeedItem

object RoomFeedItemMapper {

	fun fromRoom(item: RoomFeedItem, images: List<RoomFeedImage>): BundledFeedItem {
		return FeedItem.Builder()
				.setId(item.id)
				.setTitle(item.title)
				.setContent(item.content)
				.setPublicationDate(item.publicationDate)
				.setAvatarImageUrl(item.avatarImageUrl)
				.setImages(images.map(RoomFeedImageMapper::fromRoom))
				.build()
				.toBundledItem(pluginClassName = item.pluginClassName)
	}

	fun toRoom(input: BundledFeedItem): RoomFeedItem {
		return with(input) {
			RoomFeedItem(id, title, content, publicationDate, avatarImageUrl, pluginClassName)
		}
	}
}
