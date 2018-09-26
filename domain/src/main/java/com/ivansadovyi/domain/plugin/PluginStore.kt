package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.Store
import com.ivansadovyi.sdk.OneFeedPlugin

interface PluginStore : Store<PluginStore> {

	val plugins: List<OneFeedPlugin>

}
