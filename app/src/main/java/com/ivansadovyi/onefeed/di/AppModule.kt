package com.ivansadovyi.onefeed.di

import com.ivansadovyi.domain.app.AppInteractor
import com.ivansadovyi.domain.app.AppRouter
import com.ivansadovyi.domain.app.AppVersionRepository
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.log.LoggingInteractor
import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorInteractor
import com.ivansadovyi.onefeed.presentation.AppRouterImpl
import dagger.Lazy
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val currentAppVersion: Int) {

	@Provides
	@Singleton
	fun provideAppInteractor(
			pluginInteractor: Lazy<PluginInteractor>,
			pluginDescriptorInteractor: Lazy<PluginDescriptorInteractor>,
			feedItemsInteractor: Lazy<FeedItemsInteractor>,
			loggingInteractor: Lazy<LoggingInteractor>,
			appVersionRepository: Lazy<AppVersionRepository>
	): AppInteractor {
		return AppInteractor(
				currentAppVersion = currentAppVersion,
				pluginInteractor = pluginInteractor,
				pluginDescriptorInteractor = pluginDescriptorInteractor,
				feedItemsInteractor = feedItemsInteractor,
				loggingInteractor = loggingInteractor,
				appVersionRepository = appVersionRepository
		)
	}

	@Provides
	@Singleton
	fun provideAppRouter(impl: AppRouterImpl): AppRouter {
		return impl
	}
}
