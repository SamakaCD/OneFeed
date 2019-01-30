package com.ivansadovyi.domain.feed

import com.ivansadovyi.domain.utils.ObservableStore
import com.ivansadovyi.domain.utils.ObservableValue
import com.ivansadovyi.sdk.FeedItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedItemsStoreImpl @Inject constructor(
		private val feedItemsDao: FeedItemsDao
) : ObservableStore<FeedItemsStore>(), FeedItemsStore {

	override var items: List<FeedItem> by ObservableValue(defaultValue = mutableListOf())

	override var loading by ObservableValue(defaultValue = false)

	init {
		observeDao()
	}

	private fun observeDao() {
		feedItemsDao.getFeedItems().subscribe {
			items = it
		}
	}
}
