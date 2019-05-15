package com.ivansadovyi.onefeed.presentation.screens.feed

import com.ivansadovyi.sdk.OneFeedPluginDescriptor

interface FeedRouter {

	fun navigateToPluginAuthorization(pluginDescriptor: OneFeedPluginDescriptor)

	fun navigateToFeedItemDetails(itemId: String)
}
