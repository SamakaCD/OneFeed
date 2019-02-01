package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.plugin.PluginInvocationException
import com.ivansadovyi.sdk.OneFeedPlugin

class RateLimitException(plugin: OneFeedPlugin) : PluginInvocationException(plugin)
