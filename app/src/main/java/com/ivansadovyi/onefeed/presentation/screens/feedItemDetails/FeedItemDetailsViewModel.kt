package com.ivansadovyi.onefeed.presentation.screens.feedItemDetails

import android.graphics.Bitmap
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.onefeed.BR
import com.ivansadovyi.onefeed.presentation.utils.ObservableField
import javax.inject.Inject

class FeedItemDetailsViewModel @Inject constructor(
		private val feedItemsStore: FeedItemsStore
) : BaseObservable() {

	@get:Bindable
	var feedItem: BundledFeedItem? by ObservableField(fieldId = BR.feedItem, value = null)

	@get:Bindable
	var pluginIcon: Bitmap? by ObservableField(fieldId = BR.pluginIcon, value = null)

	@Bindable("feedItem")
	fun isDateVisible(): Boolean {
		return feedItem?.isDateVisible == true && feedItem?.publicationDate != null
	}

	private lateinit var itemId: String

	fun onInit(itemId: String) {
		this.itemId = itemId
	}

	fun onResume() {
		feedItem = feedItemsStore.getItem(itemId)
	}
}
