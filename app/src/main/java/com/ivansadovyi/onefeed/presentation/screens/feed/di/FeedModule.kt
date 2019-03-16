package com.ivansadovyi.onefeed.presentation.screens.feed.di

import com.ivansadovyi.onefeed.presentation.GenericExceptionHandler
import com.ivansadovyi.onefeed.presentation.screens.feed.FeedActivity
import com.ivansadovyi.onefeed.presentation.screens.feed.FeedRouter
import com.ivansadovyi.onefeed.presentation.screens.feed.FeedView
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class FeedModule {

	@Binds
	abstract fun bindView(activity: FeedActivity): FeedView

	@Binds
	abstract fun bindRouter(impl: FeedActivity): FeedRouter

	@Module
	companion object {

		@JvmStatic
		@Provides
		fun provideGenericExceptionHandler(activity: FeedActivity): GenericExceptionHandler {
			return GenericExceptionHandler(activity)
		}
	}
}
