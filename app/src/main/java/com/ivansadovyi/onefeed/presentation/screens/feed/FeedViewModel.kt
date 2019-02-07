package com.ivansadovyi.onefeed.presentation.screens.feed

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ivansadovyi.domain.app.AppInteractor
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorStore
import com.ivansadovyi.onefeed.presentation.GenericExceptionHandler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class FeedViewModel @Inject constructor(
		private val feedRouter: FeedRouter,
		private val feedItemsStore: FeedItemsStore,
		private val feedItemsInteractor: FeedItemsInteractor,
		private val appInteractor: AppInteractor,
		private val pluginInteractor: PluginInteractor,
		private val pluginDescriptorStore: PluginDescriptorStore,
		private val exceptionHandler: GenericExceptionHandler
) : BaseObservable() {

	private val disposable = CompositeDisposable()

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

		if (feedItemsStore.loading && feedItemsStore.items.isNotEmpty()) {
			items.add(PaginationLoadingItem())
		}

		return items
	}

	@Bindable
	fun isRefreshing(): Boolean {
		return feedItemsStore.refreshing
	}

	private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

	init {
		bindStore()
		coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
			appInteractor.init()
		}
	}

	fun authorizeTwitter() {
		val twitterPluginDescriptor = pluginDescriptorStore.pluginDescriptors.find { it.name.contains("Twitter") }
		if (twitterPluginDescriptor != null) {
			feedRouter.navigateToPluginAuthorization(twitterPluginDescriptor)
		}
	}

	fun loadMore() {
		coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
			feedItemsInteractor.loadMore()
		}
	}

	fun refresh() {
		coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
			feedItemsInteractor.refresh()
		}
	}

	fun resetAuthorizations() {
		coroutineScope.launch(exceptionHandler.coroutineExceptionHandler) {
			pluginInteractor.resetAuthorizations()
		}
	}

	private fun bindStore() {
		disposable += feedItemsStore.observable.subscribe {
			notifyChange()
		}
	}
}
