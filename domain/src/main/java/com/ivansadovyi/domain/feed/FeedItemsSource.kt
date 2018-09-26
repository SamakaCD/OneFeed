package com.ivansadovyi.domain.feed

import com.ivansadovyi.sdk.FeedItem
import io.reactivex.Single

interface FeedItemsSource {

	fun refresh(): Single<List<FeedItem>>

	fun loadNext(): Single<List<FeedItem>>

}
