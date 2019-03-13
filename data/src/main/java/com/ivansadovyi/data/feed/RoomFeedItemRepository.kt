package com.ivansadovyi.data.feed

import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.FeedItemRepository
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomFeedItemRepository(private val feedItemDao: RoomFeedItemDao) : FeedItemRepository {

	override suspend fun clear() = withContext(Dispatchers.IO) {
		feedItemDao.deleteAll()
	}

	override suspend fun putFeedItems(items: List<BundledFeedItem>) = withContext(Dispatchers.IO) {
		feedItemDao.insert(items.map(::FeedItemWithAttachments))
	}

	override fun getFeedItems(): Observable<List<BundledFeedItem>> {
		return feedItemDao.getAll().map { items ->
			items.map { RoomFeedItemMapper.fromRoom(it.item, it.images, it.subItems) }
		}
	}
}
