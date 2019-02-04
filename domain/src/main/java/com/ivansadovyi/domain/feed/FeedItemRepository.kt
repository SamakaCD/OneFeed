package com.ivansadovyi.domain.feed

import com.ivansadovyi.sdk.FeedItem
import io.reactivex.Observable

interface FeedItemRepository {

	suspend fun clear()

	suspend fun putFeedItems(items: List<FeedItem>)

	fun getFeedItems(): Observable<List<FeedItem>>

}
