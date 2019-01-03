package com.ivansadovyi.onefeed.plugin.twitter

import com.ivansadovyi.sdk.FeedItem
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.AuthorizationHandler

class TwitterPlugin : OneFeedPlugin() {

	override fun getAuthorizationHandler(): AuthorizationHandler {
		return TwitterAuthorizationHandler()
	}

	override fun loadNextItems(): Iterable<FeedItem> {
		return emptyList()
	}

	companion object {

		val DESCRIPTOR = OneFeedPluginDescriptor.Builder()
				.setName("Twitter")
				.setClassName(TwitterPlugin::javaClass.name)
				.build()
	}
}