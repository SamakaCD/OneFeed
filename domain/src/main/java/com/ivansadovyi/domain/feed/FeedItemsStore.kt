package com.ivansadovyi.domain.feed

import com.ivansadovyi.domain.feed.FeedAction.*
import com.ivansadovyi.domain.utils.truba.Store
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedItemsStore @Inject constructor(
		private val feedItemRepository: FeedItemRepository
) : Store<FeedAction>() {

	var items: List<BundledFeedItem> = emptyList()
	var loading = false
	var refreshing = false

	private var socialFilter: String? = null

	init {
		observeDao()
	}

	fun getItem(itemId: String): BundledFeedItem? {
		return items.find { it.id == itemId }
	}

	fun getLikedItems(): List<BundledFeedItem> {
		return items.filter { it.isLiked }
	}

	fun finishLoading() {
		loading = false
		notifyChange(LoadingFinishedAction)
	}

	fun finishRefreshing() {
		loading = false
		refreshing = false
		notifyChange(RefreshingFinishedAction)
	}

	fun startLoading() {
		loading = true
		notifyChange(LoadingStartedAction)
	}

	fun startRefreshing() {
		loading = true
		refreshing = true
		notifyChange(RefreshingStartedAction)
	}

	fun setSocialFilter(filter: String) {
		socialFilter = filter
		items = items.filter { !it.pluginClassName.contains(filter) }
		notifyChange(FeedAction.RepositoryUpdateAction)
	}

	private fun observeDao() {
		feedItemRepository.observeFeedItems {
			items = it
					.let {
						val sf = socialFilter
						if (sf != null) {
							it.filter { !it.pluginClassName.contains(sf) }
						} else {
							it
						}
					}

			notifyChange(RepositoryUpdateAction)
		}
	}
}
