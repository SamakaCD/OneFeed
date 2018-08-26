package com.ivansadovyi.onefeed.presentation.screens.feed

import android.databinding.BaseObservable
import com.ivansadovyi.domain.feed.FeedItemsStore
import javax.inject.Inject

class FeedViewModel @Inject constructor(val feedItemsStore: FeedItemsStore) : BaseObservable() {

	init {
		bindStore()
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
