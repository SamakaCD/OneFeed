package com.ivansadovyi.domain.plugin.di

import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.domain.plugin.PluginStoreImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class PluginStoreDaggerBindings {

	@Binds
	@Singleton
	abstract fun bindPluginStore(impl: PluginStoreImpl): PluginStore

}
