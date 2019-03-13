package com.ivansadovyi.domain.plugin.descriptor

import com.ivansadovyi.domain.utils.truba.Action

sealed class PluginDescriptorAction : Action() {

	object PluginDescriptorsLoadedAction : PluginDescriptorAction()
}
