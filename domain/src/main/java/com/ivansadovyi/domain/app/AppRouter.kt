package com.ivansadovyi.domain.app

import com.ivansadovyi.sdk.OneFeedPluginDescriptor

interface AppRouter {

	fun navigateToPluginAuthorization(pluginDescriptor: OneFeedPluginDescriptor)
}
