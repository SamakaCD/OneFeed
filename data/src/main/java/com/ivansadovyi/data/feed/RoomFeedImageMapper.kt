package com.ivansadovyi.data.feed

import com.ivansadovyi.sdk.FeedImage

object RoomFeedImageMapper {

	fun fromRoom(input: RoomFeedImage): FeedImage {
		return FeedImage.Builder()
				.setId(input.id)
				.setUrl(input.url)
				.build()
	}

	fun toRoom(image: FeedImage, feedItemId: String): RoomFeedImage {
		return with(image) {
			RoomFeedImage(id, feedItemId, url)
		}
	}
}
