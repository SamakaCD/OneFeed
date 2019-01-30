package com.ivansadovyi.domain.feed

interface FeedItemsInteractor {

	suspend fun clear()

	suspend fun refresh()

	suspend fun loadMore()
}
