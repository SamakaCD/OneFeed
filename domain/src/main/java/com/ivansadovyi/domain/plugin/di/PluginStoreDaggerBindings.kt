package com.ivansadovyi.domain.plugin.di

import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.domain.plugin.PluginStoreImpl
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationStore
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationStoreImpl
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorStore
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorStoreImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class PluginStoreDaggerBindings {

	@Binds
	@Singleton
	abstract fun bindPluginStore(impl: PluginStoreImpl): PluginStore

	@Binds
	@Singleton
	abstract fun bindPluginAuthorizationStore(impl: PluginAuthorizationStoreImpl): PluginAuthorizationStore

	@Binds
	@Singleton
	abstract fun bindPluginDescriptorStore(impl: PluginDescriptorStoreImpl): PluginDescriptorStore

}
