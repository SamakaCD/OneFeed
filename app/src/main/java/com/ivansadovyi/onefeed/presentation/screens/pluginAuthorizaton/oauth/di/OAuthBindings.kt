package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.oauth.di

import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.oauth.OAuthFragment
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.oauth.OAuthView
import dagger.Binds
import dagger.Module

@Module
abstract class OAuthBindings {

	@Binds
	abstract fun bindView(impl: OAuthFragment): OAuthView
}
