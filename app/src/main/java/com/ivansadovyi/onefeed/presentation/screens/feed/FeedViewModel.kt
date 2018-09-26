package com.ivansadovyi.onefeed.presentation.screens.feed

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.ivansadovyi.domain.feed.FeedItemsStore
import javax.inject.Inject

class FeedViewModel @Inject constructor(@Bindable val feedItemsStore: FeedItemsStore) : BaseObservable() {

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

	fun loadMore() {
		if (!feedItemsStore.loading) {
			feedItemsStore.loadMore()
		}
	}

	private fun bindStore() {
		feedItemsStore.observable.subscribe {
			notifyChange()
		}
	}
}
