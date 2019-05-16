package com.ivansadovyi.onefeed.presentation.di

import com.ivansadovyi.onefeed.presentation.screens.feed.FeedActivity
import com.ivansadovyi.onefeed.presentation.screens.feed.di.FeedModule
import com.ivansadovyi.onefeed.presentation.screens.feedItemDetails.FeedItemDetailsActivity
import com.ivansadovyi.onefeed.presentation.screens.feedItemDetails.di.FeedItemDetailsModule
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.PluginAuthorizationActivity
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.di.PluginAuthorizationBindings
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindings {

	@ContributesAndroidInjector(modules = [FeedModule::class])
	abstract fun bindFeedActivity(): FeedActivity

	@ContributesAndroidInjector(modules = [PluginAuthorizationBindings::class])
	abstract fun bindPluginAuthorizationActivity(): PluginAuthorizationActivity

	@ContributesAndroidInjector(modules = [FeedItemDetailsModule::class])
	abstract fun bindFeedItemDetailsActivity(): FeedItemDetailsActivity
}
