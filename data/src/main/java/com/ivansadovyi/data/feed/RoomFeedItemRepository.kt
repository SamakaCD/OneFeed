package com.ivansadovyi.data.feed

import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.FeedItemRepository
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomFeedItemRepository(private val dao: RoomFeedItemDao) : FeedItemRepository {

	override suspend fun clear() = withContext(Dispatchers.IO) {
		dao.deleteAll()
	}

	override suspend fun putFeedItems(items: List<BundledFeedItem>) = withContext(Dispatchers.IO) {
		dao.insert(items.map(RoomFeedItemMapper::toRoom))
	}

	override fun getFeedItems(): Observable<List<BundledFeedItem>> {
		return dao.getAll().map { it.map(RoomFeedItemMapper::fromRoom) }
	}
}
