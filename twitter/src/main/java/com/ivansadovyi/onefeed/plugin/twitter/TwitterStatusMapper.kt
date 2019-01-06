package com.ivansadovyi.onefeed.plugin.twitter

import com.ivansadovyi.sdk.FeedItem
import twitter4j.Status

object TwitterStatusMapper {

	fun toFeedItem(status: Status): FeedItem {
		return FeedItem(
				status.id.toString(),
				status.source ?: "NO SOURCE!",
				status.text,
				status.createdAt,
				status.user.miniProfileImageURL
		)
	}
}
