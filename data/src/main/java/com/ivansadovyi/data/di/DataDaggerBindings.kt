package com.ivansadovyi.data.di

import com.ivansadovyi.data.feed.RealmFeedItemsDao
import com.ivansadovyi.data.plugin.PluginLoaderImpl
import com.ivansadovyi.domain.feed.FeedItemsDao
import com.ivansadovyi.domain.plugin.PluginLoader
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataDaggerBindings {

	@Binds
	@Singleton
	abstract fun bindFeedItemsDao(impl: RealmFeedItemsDao): FeedItemsDao

	@Binds
	@Singleton
	abstract fun bindPluginLoader(impl: PluginLoaderImpl): PluginLoader

}
