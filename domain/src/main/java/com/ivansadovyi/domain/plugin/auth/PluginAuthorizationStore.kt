package com.ivansadovyi.domain.plugin.auth

import com.ivansadovyi.domain.Store
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import io.reactivex.Completable

interface PluginAuthorizationStore : Store<PluginAuthorizationStore> {

	val authorizations: List<PluginAuthorization>

	fun startAuthorization(pluginDescriptor: OneFeedPluginDescriptor, callbacks: PluginAuthorizationCallbacks): Completable
}
