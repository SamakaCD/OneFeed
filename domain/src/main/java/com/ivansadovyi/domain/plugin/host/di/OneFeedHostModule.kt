package com.ivansadovyi.domain.plugin.host.di

import com.ivansadovyi.domain.plugin.host.v1.OneFeedHostV1
import com.ivansadovyi.sdk.OneFeedHost
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class OneFeedHostModule {

	@Binds
	@Singleton
	abstract fun bindOneFeedHost(impl: OneFeedHostV1): OneFeedHost
}
