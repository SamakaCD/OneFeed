package com.ivansadovyi.data.feed

import com.ivansadovyi.data.DaoMapper
import com.ivansadovyi.sdk.FeedItem

object RealmFeedItemMapper : DaoMapper<FeedItem, RealmFeedItem> {

	override fun mapFromDao(item: RealmFeedItem): FeedItem {
		with(item) {
			return FeedItem(id, title, content, publicationDate, avatarImageUrl)
		}
	}

	override fun mapToDao(item: FeedItem): RealmFeedItem {
		with (item) {
			return RealmFeedItem(id, title, content, publicationDate, avatarImageUrl)
		}
	}
}
