package com.ivansadovyi.domain.di

import com.ivansadovyi.domain.feed.di.FeedItemsStoreDaggerModule
import com.ivansadovyi.domain.plugin.di.PluginStoreDaggerBindings
import dagger.Module

@Module(includes = [FeedItemsStoreDaggerModule::class, PluginStoreDaggerBindings::class])
class DomainDaggerModule
