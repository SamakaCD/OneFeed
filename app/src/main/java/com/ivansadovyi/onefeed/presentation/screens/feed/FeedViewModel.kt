package com.ivansadovyi.onefeed.presentation.screens.feed

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ivansadovyi.domain.feed.FeedItemsStore
import javax.inject.Inject

class FeedViewModel @Inject constructor(@Bindable val feedItemsStore: FeedItemsStore) : BaseObservable() {

	init {
		bindStore()
		feedItemsStore.refresh()
	}

	fun getFeedState(): FeedState {
		return when {
			feedItemsStore.loading -> FeedState.LOADING
			else -> FeedState.READY
		}
	}

	private fun bindStore() {
		feedItemsStore.observable.subscribe {
			notifyChange()
		}
	}
}
