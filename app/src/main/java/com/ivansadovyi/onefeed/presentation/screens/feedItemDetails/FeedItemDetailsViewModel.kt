package com.ivansadovyi.onefeed.presentation.screens.feedItemDetails

import android.graphics.Bitmap
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.domain.utils.Disposable
import com.ivansadovyi.onefeed.BR
import com.ivansadovyi.onefeed.presentation.GenericExceptionHandler
import com.ivansadovyi.onefeed.presentation.utils.ObservableField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedItemDetailsViewModel @Inject constructor(
		private val feedItemsStore: FeedItemsStore,
		private val feedItemsInteractor: FeedItemsInteractor,
		private val exceptionHandler: GenericExceptionHandler
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
	private var disposable: Disposable? = null
	private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

	fun onInit(itemId: String) {
		this.itemId = itemId
	}

	fun onResume() {
		feedItem = feedItemsStore.getItem(itemId)
		bindStore()
	}

	fun onPause() {
		disposable?.invoke()
	}

	fun onLike() {
		coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
			feedItem?.let { feedItem ->
				feedItemsInteractor.likeItem(feedItem)
			}
		}
	}

	private fun bindStore() {
		disposable = feedItemsStore.observe {
			coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
				feedItem = feedItemsStore.getItem(itemId)
				notifyChange()
			}
		}
	}
}
