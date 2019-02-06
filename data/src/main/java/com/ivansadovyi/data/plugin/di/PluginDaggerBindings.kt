package com.ivansadovyi.data.plugin.di

import com.ivansadovyi.data.plugin.PluginIconCacheImpl
import com.ivansadovyi.domain.plugin.PluginIconCache
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class PluginDaggerBindings {

	@Binds
	@Singleton
	abstract fun bindPluginIconCache(impl: PluginIconCacheImpl): PluginIconCache
}
