package com.ivansadovyi.onefeed.di

import android.app.Application
import com.ivansadovyi.data.di.DataDaggerModule
import com.ivansadovyi.domain.di.DomainDaggerModule
import com.ivansadovyi.onefeed.OneFeedApplication
import com.ivansadovyi.onefeed.presentation.di.ActivityBindings
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
	AndroidInjectionModule::class,
	DomainDaggerModule::class,
	DataDaggerModule::class,
	AppModule::class,
	ActivityBindings::class
])
interface AppComponent {

	fun inject(application: OneFeedApplication)

	@Component.Builder
	interface Builder {

		@BindsInstance
		fun application(application: Application): Builder

		fun build(): AppComponent
	}
}
