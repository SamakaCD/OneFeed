package com.ivansadovyi.domain.feed

import com.ivansadovyi.sdk.FeedItem
import io.reactivex.Completable
import io.reactivex.Observable

interface FeedItemsDao {

	fun putFeedItems(items: List<FeedItem>): Completable

	fun getFeedItems(): Observable<List<FeedItem>>

}
