package com.ivansadovyi.domain.plugin

import android.app.Application
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationRepository
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorInteractor
import com.ivansadovyi.domain.plugin.usecase.*
import com.ivansadovyi.sdk.FeedItem
import com.ivansadovyi.sdk.OneFeedPlugin
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
		private val pluginLoader: Provider<PluginLoader>,
		private val pluginAuthorizationRepositoryProvider: Provider<PluginAuthorizationRepository>,
		private val applicationProvider: Provider<Application>,
		private val pluginIconCache: Lazy<PluginIconCache>
) : PluginInteractor {

	override suspend fun startPluginAuthorization(pluginDescriptor: OneFeedPluginDescriptor): AuthorizationParams {
		return StartPluginAuthorizationUseCase(
				pluginDescriptor = pluginDescriptor,
				pluginStore = pluginStore.get(),
				pluginDescriptorInteractor = pluginDescriptorInteractor.get(),
				application = applicationProvider.get()
		).execute()
	}

	override suspend fun processAuthorizationResponse(pluginDescriptor: OneFeedPluginDescriptor, response: String): Boolean {
		return ProcessAuthorizationResponseUseCase(
				pluginDescriptor = pluginDescriptor,
				response = response,
				pluginStore = pluginStore.get(),
				pluginInteractor = this,
				feedItemsInteractor = feedItemsInteractor.get(),
				pluginAuthorizationRepository = pluginAuthorizationRepositoryProvider.get()
		).execute()
	}

	override suspend fun restoreAuthorizations() {
		RestorePluginAuthorizationsUseCase(
				pluginStore = pluginStore.get(),
				pluginInteractor = this,
				pluginDescriptorInteractor = pluginDescriptorInteractor.get(),
				pluginLoader = pluginLoader.get(),
				pluginAuthorizationRepository = pluginAuthorizationRepositoryProvider.get(),
				application = applicationProvider.get()
		).execute()
	}

	override suspend fun resetAuthorizations() {
		ResetAuthorizationsUseCase(
				pluginStore = pluginStore.get(),
				pluginAuthorizationRepository = pluginAuthorizationRepositoryProvider.get(),
				feedItemsInteractor = feedItemsInteractor.get()
		).execute()
	}

	override suspend fun refresh(plugin: OneFeedPlugin): Iterable<FeedItem> {
		return RefreshPluginUseCase(plugin).execute()
	}

	override suspend fun loadNextItems(plugin: OneFeedPlugin): Iterable<FeedItem> {
		return LoadNextItemsPluginUseCase(plugin).execute()
	}

	override suspend fun cachePluginIcon(plugin: OneFeedPlugin) {
		CachePluginIconUseCase(
				plugin = plugin,
				pluginIconCache = pluginIconCache.get()
		).execute()
	}
}
