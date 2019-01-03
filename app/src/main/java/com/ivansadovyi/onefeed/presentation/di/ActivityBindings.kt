package com.ivansadovyi.onefeed.presentation.di

import com.ivansadovyi.onefeed.presentation.screens.feed.FeedActivity
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.PluginAuthorizationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindings {

	@ContributesAndroidInjector
	abstract fun bindFeedActivity(): FeedActivity

	@ContributesAndroidInjector
	abstract fun bindPluginAuthorizationActivity(): PluginAuthorizationActivity

}
