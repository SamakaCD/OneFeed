package com.ivansadovyi.onefeed.presentation.screens.feed

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.ivansadovyi.onefeed.BR
import com.ivansadovyi.onefeed.presentation.utils.ObservableField
import com.ivansadovyi.sdk.FeedItem

class FeedViewModel : BaseObservable() {

	@get:Bindable
	val items = mutableListOf<FeedItem>()

	@get:Bindable
	var loading by ObservableField(fieldId = BR.loading, value = true)

	@Bindable("loading")
	fun getFeedState(): FeedState {
		return when {
			loading -> FeedState.LOADING
			else -> FeedState.READY
		}
	}
}
