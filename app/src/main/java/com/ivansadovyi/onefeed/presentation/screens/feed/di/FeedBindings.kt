package com.ivansadovyi.onefeed.presentation.screens.feed.di

import com.ivansadovyi.onefeed.presentation.screens.feed.FeedActivity
import com.ivansadovyi.onefeed.presentation.screens.feed.FeedRouter
import dagger.Binds
import dagger.Module

@Module
abstract class FeedBindings {

	@Binds
	abstract fun bindRouter(impl: FeedActivity): FeedRouter
}
