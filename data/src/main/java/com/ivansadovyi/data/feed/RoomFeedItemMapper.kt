package com.ivansadovyi.data.feed

import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.toBundledItem
import com.ivansadovyi.sdk.FeedItem

object RoomFeedItemMapper {

	fun fromRoom(item: RoomFeedItem, images: List<RoomFeedImage>, subItems: List<RoomSubItem>): BundledFeedItem {
		return FeedItem.Builder()
				.setId(item.id)
				.setTitle(item.title)
				.setContent(item.content)
				.setPublicationDate(item.publicationDate)
				.setDateVisible(item.isDateVisible)
				.setAvatarImageUrl(item.avatarImageUrl)
				.setImages(images.map(RoomFeedImageMapper::fromRoom))
				.setSubItems(subItems.map(RoomSubItemMapper::fromRoom))
				.setLikeable(item.isLikeable)
				.setLiked(item.isLiked)
				.setLikesCount(item.likesCount)
				.build()
				.toBundledItem(pluginClassName = item.pluginClassName)
	}

	fun toRoom(input: BundledFeedItem): RoomFeedItem {
		return with(input) {
			RoomFeedItem(id, title, content, publicationDate, isDateVisible, avatarImageUrl,
					pluginClassName, isLikeable, isLiked, likesCount)
		}
	}
}
