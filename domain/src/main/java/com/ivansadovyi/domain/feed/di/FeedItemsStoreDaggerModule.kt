package com.ivansadovyi.domain.feed.di

import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.feed.FeedItemsInteractorImpl
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.domain.feed.FeedItemsStoreImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class FeedItemsStoreDaggerModule {

	@Binds
	@Singleton
	abstract fun bindFeedItemsStore(impl: FeedItemsStoreImpl): FeedItemsStore

	@Binds
	@Singleton
	abstract fun bindFeedItemsInteractor(impl: FeedItemsInteractorImpl): FeedItemsInteractor
}
