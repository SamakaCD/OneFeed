package com.ivansadovyi.domain.plugin.usecase

import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.log.LoggingInteractor
import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.SubItem

class HandleSubItemClickUseCase(
		private val subItem: SubItem,
		private val feedItem: BundledFeedItem,
		private val loggingInteractor: LoggingInteractor,
		private val pluginStore: PluginStore
) : PluginInvocationUseCase<Unit>() {

	override suspend fun execute() {
		val plugin = pluginStore.plugins.find { it.descriptor.className == feedItem.pluginClassName }
		if (plugin == null) {
			val message = "Can not find plugin with class name ${feedItem.pluginClassName}"
			loggingInteractor.warning(this, message)
			throw IllegalArgumentException(message)
		}

		pluginInvocation(plugin) {
			if (plugin is OneFeedPlugin.OnSubItemClickListener) {
				plugin.onSubItemClick(subItem, feedItem)
			} else {
				loggingInteractor.warning(this@HandleSubItemClickUseCase,
						"$plugin does not implement OneFeedPlugin.OnSubItemClickListener " +
								"but SubItem has been clicked.")
			}
		}
	}
}
