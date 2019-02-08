package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.di

import com.ivansadovyi.onefeed.presentation.GenericExceptionHandler
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.PluginAuthorizationActivity
import dagger.Module
import dagger.Provides

@Module
class PluginAuthorizationModule {

	@Provides
	fun provideGenericExceptionHandler(activity: PluginAuthorizationActivity): GenericExceptionHandler {
		return GenericExceptionHandler(activity)
	}
}
