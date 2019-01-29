package com.ivansadovyi.domain.feed

interface FeedItemsInteractor {

	suspend fun refresh()

	suspend fun loadMore()
}
