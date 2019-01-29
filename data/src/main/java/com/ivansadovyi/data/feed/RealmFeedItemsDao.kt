package com.ivansadovyi.data.feed

import com.ivansadovyi.data.di.qualifiers.RealmScheduler
import com.ivansadovyi.domain.feed.FeedItemsDao
import com.ivansadovyi.sdk.FeedItem
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.realm.Realm
import io.realm.Sort
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealmFeedItemsDao @Inject constructor(
		private val realm: Realm,

		@RealmScheduler
		private val realmScheduler: Scheduler,

		@RealmScheduler
		private val coroutineDispatcher: CoroutineDispatcher
) : FeedItemsDao {

	override fun getFeedItems(): Observable<List<FeedItem>> {
		return realm.where(RealmFeedItem::class.java)
				.sort(FIELD_PUBLICATION_DATE, Sort.DESCENDING)
				.findAll()
				.asFlowable()
				.subscribeOn(realmScheduler)
				.toObservable()
				.map { it.map(RealmFeedItemMapper::mapFromDao) }
	}

	override suspend fun putFeedItems(items: List<FeedItem>) = withContext(coroutineDispatcher) {
		realm.executeTransaction {
			items.forEach { item ->
				realm.copyToRealmOrUpdate(RealmFeedItemMapper.mapToDao(item))
			}
		}
	}

	companion object {
		private const val FIELD_PUBLICATION_DATE = "publicationDate"
	}
}
