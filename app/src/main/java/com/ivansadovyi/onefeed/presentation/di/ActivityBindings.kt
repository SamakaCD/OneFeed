package com.ivansadovyi.onefeed.presentation.di

import com.ivansadovyi.onefeed.presentation.screens.feed.FeedActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindings {

	@ContributesAndroidInjector
	abstract fun bindFeedActivity(): FeedActivity

}