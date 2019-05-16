package com.ivansadovyi.onefeed.presentation.screens.feedItemDetails.di

import com.ivansadovyi.onefeed.presentation.GenericExceptionHandler
import com.ivansadovyi.onefeed.presentation.screens.feedItemDetails.FeedItemDetailsActivity
import dagger.Module
import dagger.Provides

@Module
abstract class FeedItemDetailsModule {

	@Module
	companion object {

		@JvmStatic
		@Provides
		fun provideGenericExceptionHandler(activity: FeedItemDetailsActivity): GenericExceptionHandler {
			return GenericExceptionHandler(activity)
		}
	}
}