package com.ivansadovyi.data.plugin.di

import android.app.Application
import com.ivansadovyi.data.db.AppDatabase
import com.ivansadovyi.data.plugin.authorization.RoomPluginAuthorizationRepository
import com.ivansadovyi.data.plugin.loader.BuiltInPluginLoader
import com.ivansadovyi.data.plugin.loader.CompositePluginLoader
import com.ivansadovyi.data.plugin.loader.app.AppPluginLoader
import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [PluginDaggerBindings::class])
class PluginDaggerModule {

	@Provides
	@Singleton
	fun providePluginLoader(application: Application): PluginLoader {
		return CompositePluginLoader(BuiltInPluginLoader(), AppPluginLoader(application))
	}

	@Provides
	@Singleton
	fun providePluginAuthorizationRepository(appDatabase: AppDatabase): PluginAuthorizationRepository {
		val dao = appDatabase.getPluginAuthorizationDao()
		return RoomPluginAuthorizationRepository(dao)
	}
}
