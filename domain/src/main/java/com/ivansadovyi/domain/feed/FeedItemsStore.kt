package com.ivansadovyi.domain.feed

import com.ivansadovyi.domain.Store

interface FeedItemsStore : Store<FeedItemsStore> {

	var items: List<BundledFeedItem>

	var loading: Boolean

	var refreshing: Boolean
}
