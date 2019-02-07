package com.ivansadovyi.data.feed

import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.FeedItemRepository
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomFeedItemRepository(
		private val feedItemDao: RoomFeedItemDao,
		private val feedImageDao: RoomFeedImageDao
) : FeedItemRepository {

	override suspend fun clear() = withContext(Dispatchers.IO) {
		feedItemDao.deleteAll()
		feedImageDao.deleteAll()
	}

	override suspend fun putFeedItems(items: List<BundledFeedItem>) = withContext(Dispatchers.IO) {
		items.forEach { item ->
			feedItemDao.insert(RoomFeedItemMapper.toRoom(item))
			feedImageDao.insertImages(item.images.map { RoomFeedImageMapper.toRoom(it, item.id) })
		}
	}

	override fun getFeedItems(): Observable<List<BundledFeedItem>> {
		return feedItemDao.getAll().map { items ->
			items.map { RoomFeedItemMapper.fromRoom(it.item, it.images) }
		}
	}
}
