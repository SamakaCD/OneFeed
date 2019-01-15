package com.ivansadovyi.domain.plugin.descriptor

import com.ivansadovyi.domain.utils.truba.ErrorAction
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.OneFeedPluginParams

sealed class PluginDescriptorActions {

	class FetchDescriptors

	class FetchDescriptorsSucceeded(val descriptors: List<OneFeedPluginDescriptor>)

	sealed class InstantiatePluginActions {

		class InstantiatePlugin(
				val pluginDescriptor: OneFeedPluginDescriptor,
				val params: OneFeedPluginParams
		)

		class InstantiatePluginSucceeded(
				val pluginDescriptor: OneFeedPluginDescriptor,
				val instance: OneFeedPlugin
		)

		class InstantiatePluginFailed(
				val pluginDescriptor: OneFeedPluginDescriptor,
				override val throwable: Throwable
		) : ErrorAction
	}
}
