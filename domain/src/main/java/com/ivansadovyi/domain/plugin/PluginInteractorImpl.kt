package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationsDao
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorInteractor
import com.ivansadovyi.domain.plugin.usecase.ProcessAuthorizationResponseUseCase
import com.ivansadovyi.domain.plugin.usecase.StartPluginAuthorizationUseCase
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.AuthorizationParams
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class PluginInteractorImpl @Inject constructor(
		private val pluginStore: Lazy<PluginStore>,
		private val pluginDescriptorInteractor: Lazy<PluginDescriptorInteractor>,
		private val feedItemsInteractor: Lazy<FeedItemsInteractor>,
		private val pluginAuthorizationsDaoProvider: Provider<PluginAuthorizationsDao>
) : PluginInteractor {

	override suspend fun startPluginAuthorization(pluginDescriptor: OneFeedPluginDescriptor): AuthorizationParams {
		return StartPluginAuthorizationUseCase(
				pluginDescriptor = pluginDescriptor,
				pluginStore = pluginStore.get(),
				pluginDescriptorInteractor = pluginDescriptorInteractor.get()
		).execute()
	}

	override suspend fun processAuthorizationResponse(pluginDescriptor: OneFeedPluginDescriptor, response: String): Boolean {
		return ProcessAuthorizationResponseUseCase(
				pluginDescriptor = pluginDescriptor,
				response = response,
				pluginStore = pluginStore.get(),
				feedItemsInteractor = feedItemsInteractor.get(),
				pluginAuthorizationsDao = pluginAuthorizationsDaoProvider.get()
		).execute()
	}
}
