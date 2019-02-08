package com.ivansadovyi.onefeed.presentation.screens.feed.di

import com.ivansadovyi.onefeed.presentation.GenericExceptionHandler
import com.ivansadovyi.onefeed.presentation.screens.feed.FeedActivity
import dagger.Module
import dagger.Provides

@Module
class FeedModule {

	@Provides
	fun provideGenericExceptionHandler(activity: FeedActivity): GenericExceptionHandler {
		return GenericExceptionHandler(activity)
	}
}
