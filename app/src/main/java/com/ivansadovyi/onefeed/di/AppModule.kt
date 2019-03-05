package com.ivansadovyi.onefeed.di

import com.ivansadovyi.domain.app.AppRouter
import com.ivansadovyi.onefeed.presentation.AppRouterImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

	@Binds
	@Singleton
	abstract fun provideAppRouter(impl: AppRouterImpl): AppRouter
}
