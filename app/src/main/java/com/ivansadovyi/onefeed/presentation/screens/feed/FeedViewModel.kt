package com.ivansadovyi.onefeed.presentation.screens.feed

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.onefeed.BR
import com.ivansadovyi.onefeed.presentation.utils.ObservableField
import com.ivansadovyi.sdk.FeedItem
import javax.inject.Inject

class FeedViewModel @Inject constructor(private val feedItemsStore: FeedItemsStore) : BaseObservable() {

	@get:Bindable
	var items by ObservableField(fieldId = BR.items, value = emptyList<FeedItem>())

	@get:Bindable
	var loading by ObservableField(fieldId = BR.loading, value = false)

	init {
		bindStore()
	}

	@Bindable("loading")
	fun getFeedState(): FeedState {
		return when {
			loading -> FeedState.LOADING
			else -> FeedState.READY
		}
	}

	private fun bindStore() {
		feedItemsStore.observable.subscribe {
			items = it.items
			loading = it.loading
		}
	}
}
