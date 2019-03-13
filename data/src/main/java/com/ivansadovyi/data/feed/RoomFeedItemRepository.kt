package com.ivansadovyi.data.feed

import androidx.room.InvalidationTracker
import com.ivansadovyi.data.db.AppDatabase
import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.FeedItemRepository
import com.ivansadovyi.domain.utils.Consumer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomFeedItemRepository(
		private val database: AppDatabase,
		private val feedItemDao: RoomFeedItemDao
) : FeedItemRepository {

	override suspend fun clear() = withContext(Dispatchers.IO) {
		feedItemDao.deleteAll()
	}

	override suspend fun clearAndPutItems(items: List<BundledFeedItem>) = withContext(Dispatchers.IO) {
		feedItemDao.deleteAllAndInsert(items.map(::FeedItemWithAttachments))
	}

	override suspend fun putFeedItems(items: List<BundledFeedItem>) = withContext(Dispatchers.IO) {
		feedItemDao.insert(items.map(::FeedItemWithAttachments))
	}

	override fun observeFeedItems(invalidationConsumer: Consumer<List<BundledFeedItem>>) {
		// Trigger observer with current feed contents
		val currentItems = feedItemDao.getAll().map { RoomFeedItemMapper.fromRoom(it.item, it.images, it.subItems) }
		invalidationConsumer.invoke(currentItems)

		val observer = object : InvalidationTracker.Observer(RoomFeedItem.TABLE_NAME) {
			override fun onInvalidated(tables: MutableSet<String>) {
				val invalidatedItems = feedItemDao.getAll()
						.map { RoomFeedItemMapper.fromRoom(it.item, it.images, it.subItems) }

				invalidationConsumer.invoke(invalidatedItems)
			}
		}
		database.invalidationTracker.addObserver(observer)
	}
}
