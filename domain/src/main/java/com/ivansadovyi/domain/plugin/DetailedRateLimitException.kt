package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.plugin.PluginInvocationException
import com.ivansadovyi.sdk.OneFeedPlugin

class DetailedRateLimitException(plugin: OneFeedPlugin) : PluginInvocationException(plugin)
