package com.ivansadovyi.domain.feed

import com.ivansadovyi.domain.utils.ObservableStore
import com.ivansadovyi.domain.utils.ObservableValue
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedItemsStoreImpl @Inject constructor(
		private val feedItemRepository: FeedItemRepository
) : ObservableStore<FeedItemsStore>(), FeedItemsStore {

	override var items: List<BundledFeedItem> by ObservableValue(defaultValue = mutableListOf())
	override var loading by ObservableValue(defaultValue = false)
	override var refreshing by ObservableValue(defaultValue = false)

	init {
		observeDao()
	}

	private fun observeDao() {
		feedItemRepository.getFeedItems().subscribe {
			items = it
		}
	}
}
