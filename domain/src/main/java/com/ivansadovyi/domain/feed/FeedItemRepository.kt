package com.ivansadovyi.domain.feed

import com.ivansadovyi.domain.utils.Consumer

interface FeedItemRepository {

	suspend fun clear()

	suspend fun clearAndPutItems(items: List<BundledFeedItem>)

	suspend fun putFeedItems(items: List<BundledFeedItem>)

	suspend fun update(item: BundledFeedItem)

	fun observeFeedItems(invalidationConsumer: Consumer<List<BundledFeedItem>>)
}
