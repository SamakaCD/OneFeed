package com.ivansadovyi.data.di

import android.app.Application
import androidx.room.Room
import com.ivansadovyi.data.db.AppDatabase
import com.ivansadovyi.data.feed.RoomFeedItemRepository
import com.ivansadovyi.data.plugin.di.PluginDaggerModule
import com.ivansadovyi.domain.feed.FeedItemRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [PluginDaggerModule::class])
class DataDaggerModule {

	@Provides
	@Singleton
	fun provideAppDatabase(application: Application): AppDatabase {
		return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.NAME).build()
	}

	@Provides
	@Singleton
	fun provideFeedItemRepository(appDatabase: AppDatabase): FeedItemRepository {
		val dao = appDatabase.getFeedItemDao()
		return RoomFeedItemRepository(dao)
	}
}
