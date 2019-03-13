package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.utils.truba.Action
import com.ivansadovyi.sdk.OneFeedPlugin

sealed class PluginAction : Action() {

	class NewPluginInstanceAction(val plugin: OneFeedPlugin) : PluginAction()

	object ClearPluginInstancesAction : PluginAction()
}
