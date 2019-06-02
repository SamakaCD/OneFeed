package com.ivansadovyi.onefeed.presentation.screens.likedItems

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.domain.feed.toBundledItem
import com.ivansadovyi.domain.utils.Disposable
import com.ivansadovyi.onefeed.presentation.GenericExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class LikedItemsViewModel @Inject constructor(
		private val feedItemsStore: FeedItemsStore,
		private val exceptionHandler: GenericExceptionHandler
) : BaseObservable() {

	@Bindable
	fun getItems(): List<BundledFeedItem> {
		return feedItemsStore.getLikedItems()
				.map {
					it.builder
							.setLikeable(false)
							.build()
							.toBundledItem(it.pluginClassName)
				}
	}

	private var disposable: Disposable? = null
	private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

	init {
		bindStore()
	}

	private fun bindStore() {
		disposable = feedItemsStore.observe {
			coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
				notifyChange()
			}
		}
	}
}