package com.ivansadovyi.onefeed

import com.ivansadovyi.sdk.FeedItem
import com.ivansadovyi.sdk.OneFeedPlugin
import io.reactivex.Observable
import java.util.*

class TestPlugin : OneFeedPlugin() {

	override fun loadNextItems(lastItemId: String?): Observable<FeedItem> {
		return Observable.just(
				FeedItem(uuid(), "First item", "First item description"),
				FeedItem(uuid(), "Second item", "Second item description"),
				FeedItem(uuid(), "Third item", "Third item description")
		)
	}

	private fun uuid(): String {
		return UUID.randomUUID().toString()
	}
}
