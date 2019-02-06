package com.ivansadovyi.domain.feed

import com.ivansadovyi.sdk.FeedItem

fun FeedItem.toBundledItem(pluginClassName: String): BundledFeedItem {
	return BundledFeedItem(this, pluginClassName)
}

class BundledFeedItem(feedItem: FeedItem, val pluginClassName: String) : FeedItem(feedItem)
