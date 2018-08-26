package com.ivansadovyi.domain.feed

import com.ivansadovyi.domain.Store
import com.ivansadovyi.sdk.FeedItem

interface FeedItemsStore : Store<FeedItemsStore> {

	var items: List<FeedItem>

	var loading: Boolean

}
