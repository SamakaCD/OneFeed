package com.ivansadovyi.data.plugin.di

import android.app.Application
import com.ivansadovyi.data.plugin.loader.BuiltInPluginLoader
import com.ivansadovyi.data.plugin.loader.CompositePluginLoader
import com.ivansadovyi.data.plugin.loader.app.AppPluginLoader
import com.ivansadovyi.domain.plugin.PluginLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PluginDaggerModule() {

	@Provides
	@Singleton
	fun providePluginLoader(application: Application): PluginLoader {
		return CompositePluginLoader(BuiltInPluginLoader(), AppPluginLoader(application))
	}
}
