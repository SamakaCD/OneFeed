package com.ivansadovyi.domain.plugin

import com.ivansadovyi.sdk.OneFeedPlugin

open class PluginInvocationException(val plugin: OneFeedPlugin): RuntimeException()
