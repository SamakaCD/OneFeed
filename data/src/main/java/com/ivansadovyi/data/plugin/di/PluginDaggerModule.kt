package com.ivansadovyi.data.plugin.di

import android.app.Application
import com.ivansadovyi.data.db.AppDatabase
import com.ivansadovyi.data.plugin.PluginIconCacheImpl
import com.ivansadovyi.data.plugin.authorization.RoomPluginAuthorizationRepository
import com.ivansadovyi.data.plugin.loader.BuiltInPluginLoaderImpl
import com.ivansadovyi.data.plugin.loader.CompositePluginLoader
import com.ivansadovyi.data.plugin.loader.app.AppPluginLoader
import com.ivansadovyi.domain.plugin.BuiltInPluginLoader
import com.ivansadovyi.domain.plugin.PluginIconCache
import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class PluginDaggerModule {

	@Binds
	@Singleton
	abstract fun bindBuiltInPluginLoader(impl: BuiltInPluginLoaderImpl): BuiltInPluginLoader

	@Binds
	@Singleton
	abstract fun bindPluginIconCache(impl: PluginIconCacheImpl): PluginIconCache

	@Module
	companion object {

		@JvmStatic
		@Provides
		@Singleton
		fun providePluginLoader(application: Application, builtInPluginLoader: BuiltInPluginLoader): PluginLoader {
			return CompositePluginLoader(builtInPluginLoader, AppPluginLoader(application))
		}

		@JvmStatic
		@Provides
		@Singleton
		fun providePluginAuthorizationRepository(appDatabase: AppDatabase): PluginAuthorizationRepository {
			val dao = appDatabase.getPluginAuthorizationDao()
			return RoomPluginAuthorizationRepository(dao)
		}
	}
}
