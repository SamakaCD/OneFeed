package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.di

import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.PluginAuthorizationActivity
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.PluginAuthorizationView
import dagger.Binds
import dagger.Module

@Module(includes = [PluginAuthorizationModule::class])
abstract class PluginAuthorizationBindings {

	@Binds
	abstract fun bindView(impl: PluginAuthorizationActivity): PluginAuthorizationView
}
