package com.ivansadovyi.onefeed.presentation.screens.feed

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ivansadovyi.domain.app.AppInteractor
import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.FeedAction
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorStore
import com.ivansadovyi.domain.utils.Disposable
import com.ivansadovyi.onefeed.presentation.GenericExceptionHandler
import com.ivansadovyi.sdk.SubItem
import dagger.Lazy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class FeedViewModel @Inject constructor(
		private val feedView: FeedView,
		private val feedRouter: FeedRouter,
		private val feedItemsStore: FeedItemsStore,
		private val feedItemsInteractor: Lazy<FeedItemsInteractor>,
		private val appInteractor: AppInteractor,
		private val pluginInteractor: Lazy<PluginInteractor>,
		private val pluginDescriptorStore: PluginDescriptorStore,
		private val exceptionHandler: GenericExceptionHandler
) : BaseObservable() {

	@Bindable
	fun getFeedState(): FeedState {
		return when {
			feedItemsStore.loading && feedItemsStore.items.isEmpty() -> FeedState.LOADING
			!feedItemsStore.loading && feedItemsStore.items.isEmpty() -> FeedState.EMPTY
			else -> FeedState.READY
		}
	}

	@Bindable
	fun getItems(): List<Any> {
		val items = ArrayList<Any>(feedItemsStore.items)

		if (feedItemsStore.loading && !feedItemsStore.refreshing && feedItemsStore.items.isNotEmpty()) {
			items.add(PaginationLoadingItem())
		}

		return items
	}

	@Bindable
	fun isRefreshing(): Boolean {
		return feedItemsStore.refreshing
	}

	private var disposable: Disposable? = null
	private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

	init {
		bindStore()
		coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
			appInteractor.init()
		}
	}

	fun onItemClick(feedItem: BundledFeedItem) {
		if (feedItem.isHasDetails) {
			feedRouter.navigateToFeedItemDetails(feedItem.id)
		}
	}

	fun onSubItemClick(subItem: SubItem, feedItem: BundledFeedItem) {
		coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
			pluginInteractor.get().handleSubItemClick(subItem, feedItem)
		}
	}

	fun onLikeClick(feedItem: BundledFeedItem) {
		coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
			feedItemsInteractor.get().likeItem(feedItem)
		}
	}

	fun authorizeTwitter() {
		val twitterPluginDescriptor = pluginDescriptorStore.pluginDescriptors.find { it.name.contains("Twitter") }
		if (twitterPluginDescriptor != null) {
			feedRouter.navigateToPluginAuthorization(twitterPluginDescriptor)
		}
	}

	fun authorizePluginRecommendations() {
		val pluginDescriptor = pluginDescriptorStore.pluginDescriptors.find { it.name.contains("Plugin recommendations") }
		if (pluginDescriptor != null) {
			feedRouter.navigateToPluginAuthorization(pluginDescriptor)
		}
	}

	fun loadMore() {
		coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
			feedItemsInteractor.get().loadMore()
		}
	}

	fun refresh() {
		coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
			feedItemsInteractor.get().refresh()
		}
	}

	fun resetAuthorizations() {
		coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
			pluginInteractor.get().resetAuthorizations()
		}
	}

	private fun bindStore() {
		disposable = feedItemsStore.observe { action ->
			coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
				notifyChange()
				when (action) {
					is FeedAction.RefreshingFinishedAction -> feedView.scrollToTop()
				}
			}
		}
	}
}
