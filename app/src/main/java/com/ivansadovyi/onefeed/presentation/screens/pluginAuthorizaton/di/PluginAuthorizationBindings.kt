package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.di

import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.PluginAuthorizationActivity
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.PluginAuthorizationView
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.empty.EmptyAuthorizationFragment
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.oauth.OAuthFragment
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.oauth.di.OAuthBindings
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [PluginAuthorizationModule::class])
abstract class PluginAuthorizationBindings {

	@Binds
	abstract fun bindView(impl: PluginAuthorizationActivity): PluginAuthorizationView

	@ContributesAndroidInjector(modules = [OAuthBindings::class])
	abstract fun bindOAuthFragment(): OAuthFragment

	@ContributesAndroidInjector
	abstract fun bindEmptyAuthorizationFragment(): EmptyAuthorizationFragment
}
