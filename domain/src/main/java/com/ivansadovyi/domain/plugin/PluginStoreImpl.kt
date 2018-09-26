package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.utils.ObservableStore
import com.ivansadovyi.domain.utils.ObservableValue
import com.ivansadovyi.sdk.OneFeedPlugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginStoreImpl @Inject constructor() : ObservableStore<PluginStore>(), PluginStore {

	override var plugins: MutableList<OneFeedPlugin> by ObservableValue(defaultValue = mutableListOf())

}
