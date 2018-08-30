package com.ivansadovyi.domain.feed

import com.ivansadovyi.domain.utils.ObservableStore
import com.ivansadovyi.domain.utils.ObservableValue
import com.ivansadovyi.sdk.FeedItem
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedItemsStoreImpl @Inject constructor(private val feedItemsDao: FeedItemsDao) : ObservableStore<FeedItemsStore>(), FeedItemsStore {

	override var items: List<FeedItem> by ObservableValue(defaultValue = mutableListOf())

	override var loading by ObservableValue(defaultValue = false)

	init {
		observeDao()
	}

	override fun refresh() {
		loading = true
		Completable.timer(1, TimeUnit.SECONDS)
				.andThen(test())
				.flatMapCompletable { feedItemsDao.putFeedItems(it) }
				.subscribe {
					loading = false
				}
	}

	private fun uuid(): String {
		return UUID.randomUUID().toString()
	}

	private fun test(): Single<List<FeedItem>> {
		return Single.just(listOf(
				FeedItem(uuid(), "First item", "First item description", Date(), AVATAR_URL),
				FeedItem(uuid(), "Second item", "Second item description", Date(), AVATAR_URL),
				FeedItem(uuid(), "Third item", "Third item description", Date(), AVATAR_URL)
		))
	}

	companion object {
		private const val AVATAR_URL = "https://99designs-start-attachments.imgix.net/alchemy-pictures/2016%2F02%2F22%2F04%2F24%2F31%2Fb7bd820a-ecc0-4170-8f4e-3db2e73b0f4a%2F550250_artsigma.png?auto=format&ch=Width%2CDPR&w=250&h=250"
	}

	private fun observeDao() {
		feedItemsDao.getFeedItems().subscribe {
			items = it
		}
	}
}
