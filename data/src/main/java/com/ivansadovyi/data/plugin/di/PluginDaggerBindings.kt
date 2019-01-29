package com.ivansadovyi.data.plugin.di

import com.ivansadovyi.data.plugin.authorization.RealmPluginAuthorizationsDao
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationsDao
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class PluginDaggerBindings {

	@Binds
	@Singleton
	abstract fun bindPluginAuthorizationDao(impl: RealmPluginAuthorizationsDao): PluginAuthorizationsDao
}
