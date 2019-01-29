package com.ivansadovyi.domain.plugin.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.domain.plugin.auth.AuthorizationProcessorFactory
import com.ivansadovyi.domain.plugin.auth.PluginAuthorization
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationsDao
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.OneFeedPluginParams
import com.ivansadovyi.sdk.auth.AuthorizationState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProcessAuthorizationResponseUseCase(
		private val pluginDescriptor: OneFeedPluginDescriptor,
		private val response: String,
		private val pluginStore: PluginStore,
		private val feedItemsInteractor: FeedItemsInteractor,
		private val pluginAuthorizationsDao: PluginAuthorizationsDao
) : UseCase<Boolean> {

	override suspend fun execute(): Boolean = withContext(Dispatchers.IO) {
		val plugin = pluginStore.getAuthorizingPluginByDescriptor(pluginDescriptor)
		val authParams = pluginStore.getCachedAuthorizationParams(plugin)
		val result = AuthorizationProcessorFactory.create(plugin.authorizationHandler).processResponse(response, authParams)

		// This response can not be processed. User need to do something with it
		// (should be redirected to next page or deal with it).
		if (!result.wasProcessed || result.authorization == null) {
			return@withContext false
		}

		val newParams = plugin.params.withAuthorization(result.authorization)
		plugin.onAuthorizationStateChanged(newParams)

		// Save new authorization to persistent storage
		val pluginAuthorization = PluginAuthorization(result.authorization, pluginDescriptor.className)
		pluginAuthorizationsDao.putPluginAuthorization(pluginAuthorization)

		// New plugin added, so feed should be refreshed.
		feedItemsInteractor.refresh()

		return@withContext true
	}

	private fun OneFeedPluginParams.withAuthorization(authorization: String): OneFeedPluginParams {
		return newBuilder()
				.setAuthorization(authorization)
				.setAuthorizationState(AuthorizationState.AUTHORIZED)
				.build()
	}
}
