package com.ivansadovyi.domain.di

import com.ivansadovyi.domain.feed.di.FeedItemsStoreDaggerModule
import dagger.Module

@Module(includes = [FeedItemsStoreDaggerModule::class])
class DomainDaggerModule
