package com.ivansadovyi.domain.feed

import io.reactivex.Observable

interface FeedItemRepository {

	suspend fun clear()

	suspend fun putFeedItems(items: List<BundledFeedItem>)

	fun getFeedItems(): Observable<List<BundledFeedItem>>

}
