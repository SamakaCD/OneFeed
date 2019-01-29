package com.ivansadovyi.domain.plugin.di

import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.domain.plugin.PluginInteractorImpl
import com.ivansadovyi.domain.plugin.PluginStore
import com.ivansadovyi.domain.plugin.PluginStoreImpl
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorInteractor
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorInteractorImpl
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
	abstract fun bindPluginInteractor(impl: PluginInteractorImpl): PluginInteractor

	@Binds
	@Singleton
	abstract fun bindPluginDescriptorStore(impl: PluginDescriptorStoreImpl): PluginDescriptorStore

	@Binds
	@Singleton
	abstract fun bindPluginDescriptorInteractor(impl: PluginDescriptorInteractorImpl): PluginDescriptorInteractor
}
