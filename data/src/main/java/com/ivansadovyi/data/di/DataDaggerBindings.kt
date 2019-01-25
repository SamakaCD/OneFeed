package com.ivansadovyi.data.di

import com.ivansadovyi.data.feed.RealmFeedItemsDao
import com.ivansadovyi.domain.feed.FeedItemsDao
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataDaggerBindings {

	@Binds
	@Singleton
	abstract fun bindFeedItemsDao(impl: RealmFeedItemsDao): FeedItemsDao
}
