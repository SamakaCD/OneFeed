package com.ivansadovyi.domain.feed

import com.ivansadovyi.sdk.FeedItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedBuilder @Inject constructor() {

	fun build(items: List<BundledFeedItem>): List<BundledFeedItem> {
		val result = mutableListOf<BundledFeedItem>()

		val itemsWithHighPriority = items.filter { it.priority == FeedItem.Priority.HIGH }
		result.addAll(itemsWithHighPriority)

		val itemsWithoutPriority = items.filter { it.priority == FeedItem.Priority.NONE }
		result.addAll(itemsWithoutPriority)

		return result
	}
}
