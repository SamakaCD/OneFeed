package com.ivansadovyi.testplugin

import com.ivansadovyi.sdk.FeedItem
import com.ivansadovyi.sdk.OneFeedPlugin
import io.reactivex.Observable
import java.util.*

class TestPlugin : OneFeedPlugin() {

	override fun loadNextItems(lastItemId: String?): Observable<FeedItem> {
		return Observable.just(
				FeedItem(uuid(), "First item", "First item description", Date(), AVATAR_URL),
				FeedItem(uuid(), "Second item", "Second item description", Date(), AVATAR_URL),
				FeedItem(uuid(), "Third item", "Third item description", Date(), AVATAR_URL)
		)
	}

	private fun uuid(): String {
		return UUID.randomUUID().toString()
	}

	companion object {
		private const val AVATAR_URL = "https://99designs-start-attachments.imgix.net/alchemy-pictures/2016%2F02%2F22%2F04%2F24%2F31%2Fb7bd820a-ecc0-4170-8f4e-3db2e73b0f4a%2F550250_artsigma.png?auto=format&ch=Width%2CDPR&w=250&h=250"
	}
}
