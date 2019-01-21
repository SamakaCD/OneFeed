package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.Store
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.AuthorizationParams
import io.reactivex.Single

interface PluginStore : Store<PluginStore> {

	val plugins: List<OneFeedPlugin>

	fun startAuthorization(pluginDescriptor: OneFeedPluginDescriptor): Single<AuthorizationParams>

	fun processAuthorizationResponse(pluginDescriptor: OneFeedPluginDescriptor, response: String): Single<Boolean>

}
