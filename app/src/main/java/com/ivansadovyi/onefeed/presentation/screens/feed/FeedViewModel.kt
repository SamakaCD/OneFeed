package com.ivansadovyi.onefeed.presentation.screens.feed

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ivansadovyi.domain.feed.FeedItemsStore
import java.util.*
import javax.inject.Inject

class FeedViewModel @Inject constructor(private val feedItemsStore: FeedItemsStore) : BaseObservable() {

	init {
		bindStore()
		feedItemsStore.refresh()
	}

	@Bindable
	fun getFeedState(): FeedState {
		return when {
			feedItemsStore.loading && feedItemsStore.items.isEmpty() -> FeedState.LOADING
			else -> FeedState.READY
		}
	}

	@Bindable
	fun getItems(): List<Any> {
		val items = ArrayList<Any>(feedItemsStore.items)

		if (feedItemsStore.loading && feedItemsStore.items.isNotEmpty()) {
			items.add(PaginationLoadingItem())
		}

		return items
	}

	fun loadMore() {
		feedItemsStore.loadMore()
	}

	private fun bindStore() {
		feedItemsStore.observable.subscribe {
			notifyChange()
		}
	}
}
