package com.ivansadovyi.data.feed

import com.ivansadovyi.data.di.qualifiers.RealmScheduler
import com.ivansadovyi.domain.feed.FeedItemsDao
import com.ivansadovyi.sdk.FeedItem
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.realm.Realm
import io.realm.Sort
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealmFeedItemsDao @Inject constructor(
		private val realm: Realm,

		@RealmScheduler
		private val realmScheduler: Scheduler
) : FeedItemsDao {

	override fun getFeedItems(): Observable<List<FeedItem>> {
		return realm.where(RealmFeedItem::class.java)
				.sort(FIELD_PUBLICATION_DATE, Sort.DESCENDING)
				.findAll()
				.asFlowable()
				.subscribeOn(realmScheduler)
				.toObservable()
				.map { it.map { MAPPER.mapToDomainLayerType(it) } }
	}

	override fun putFeedItems(items: List<FeedItem>): Completable {
		return Completable.fromAction {
			realm.executeTransaction {
				items.forEach {
					realm.copyToRealmOrUpdate(MAPPER.mapToDaoType(it))
				}
			}
		}.subscribeOn(realmScheduler)
	}

	companion object {
		private val MAPPER = RealmFeedItemsDaoMapper()
		private const val FIELD_PUBLICATION_DATE = "publicationDate"
	}
}
