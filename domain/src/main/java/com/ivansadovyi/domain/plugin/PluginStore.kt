package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.Store
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import io.reactivex.Completable

interface PluginStore : Store<PluginStore> {

	val plugins: List<OneFeedPlugin>

	fun startAuthorization(pluginDescriptor: OneFeedPluginDescriptor, callbacks: PluginAuthorizationCallbacks): Completable

	fun processAuthorizationResponse(pluginDescriptor: OneFeedPluginDescriptor, response: String): Completable

}
