package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.sdk.FeedItem
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.SubItem
import com.ivansadovyi.sdk.auth.AuthorizationParams

interface PluginInteractor {

	suspend fun startPluginAuthorization(pluginDescriptor: OneFeedPluginDescriptor): AuthorizationParams

	suspend fun processAuthorizationResponse(pluginDescriptor: OneFeedPluginDescriptor, response: String): Boolean

	suspend fun restoreAuthorizations()

	suspend fun resetAuthorizations()

	suspend fun refresh(plugin: OneFeedPlugin): Iterable<FeedItem>

	suspend fun loadNextItems(plugin: OneFeedPlugin): Iterable<FeedItem>

	suspend fun cachePluginIcon(plugin: OneFeedPlugin)

	suspend fun handleSubItemClick(subItem: SubItem, feedItem: BundledFeedItem)
}
