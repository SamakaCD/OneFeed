package com.ivansadovyi.domain.feed

import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.domain.utils.ObservableStore
import com.ivansadovyi.domain.utils.ObservableValue
import com.ivansadovyi.sdk.FeedItem
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedItemsStoreImpl @Inject constructor(
		private val pluginStore: PluginStore,
		private val feedItemsDao: FeedItemsDao
) : ObservableStore<FeedItemsStore>(), FeedItemsStore {

	override var items: List<FeedItem> by ObservableValue(defaultValue = mutableListOf())

	override var loading by ObservableValue(defaultValue = false)

	init {
		observeDao()
	}

	override fun refresh() {
		loading = true
		Observable.fromIterable(pluginStore.plugins)
				.flatMap { it.refresh() }
				.sorted { o1, o2 -> o2.publicationDate.compareTo(o1.publicationDate) }
				.toList()
				.flatMap { feedItemsDao.putFeedItems(it).toSingleDefault(it) }
				.subscribeBy(
						onSuccess = {
							loading = false
						}
				)
	}

	private fun observeDao() {
		feedItemsDao.getFeedItems().subscribe {
			items = it
		}
	}
}
