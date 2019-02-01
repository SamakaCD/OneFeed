package com.ivansadovyi.domain.plugin.usecase

import com.ivansadovyi.sdk.FeedItem
import com.ivansadovyi.sdk.OneFeedPlugin

class RefreshPluginUseCase(
		private val plugin: OneFeedPlugin
): PluginInvocationUseCase<Iterable<FeedItem>>(plugin) {

	override suspend fun executePluginInvocation(): Iterable<FeedItem> {
		return plugin.refresh()
	}
}
