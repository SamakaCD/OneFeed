package com.ivansadovyi.data.feed

import com.ivansadovyi.data.DaoMapper
import com.ivansadovyi.sdk.FeedItem

class RealmFeedItemsDaoMapper : DaoMapper<FeedItem, RealmFeedItem> {

	override fun mapToDomainLayerType(o: RealmFeedItem): FeedItem {
		with(o) {
			return FeedItem(id, title, content, publicationDate, avatarImageUrl)
		}
	}

	override fun mapToDaoType(o: FeedItem): RealmFeedItem {
		with (o) {
			return RealmFeedItem(id, title, content, publicationDate, avatarImageUrl)
		}
	}
}
