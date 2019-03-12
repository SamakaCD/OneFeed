package com.ivansadovyi.data.di

import android.app.Application
import androidx.room.Room
import com.ivansadovyi.data.app.AppVersionRepositoryImpl
import com.ivansadovyi.data.db.AppDatabase
import com.ivansadovyi.data.feed.RoomFeedItemRepository
import com.ivansadovyi.data.plugin.di.PluginDaggerModule
import com.ivansadovyi.domain.app.AppVersionRepository
import com.ivansadovyi.domain.feed.FeedItemRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [PluginDaggerModule::class])
abstract class DataDaggerModule {

	@Binds
	@Singleton
	abstract fun bindAppVersionRepository(impl: AppVersionRepositoryImpl): AppVersionRepository

	@Module
	companion object {

		@JvmStatic
		@Provides
		@Singleton
		fun provideAppDatabase(application: Application): AppDatabase {
			return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.NAME).build()
		}

		@JvmStatic
		@Provides
		@Singleton
		fun provideFeedItemRepository(appDatabase: AppDatabase): FeedItemRepository {
			return RoomFeedItemRepository(appDatabase.getFeedItemDao())
		}
	}
}
