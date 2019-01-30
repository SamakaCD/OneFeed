package com.ivansadovyi.data.di

import android.app.Application
import com.ivansadovyi.data.di.qualifiers.RealmScheduler
import com.ivansadovyi.data.plugin.di.PluginDaggerModule
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.Realm
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.rx2.asCoroutineDispatcher
import javax.inject.Singleton

@Module(includes = [DataDaggerBindings::class, PluginDaggerModule::class])
class DataDaggerModule {

	@Provides
	@RealmScheduler
	@Singleton
	fun provideRealmRxScheduler(): Scheduler {
		return AndroidSchedulers.mainThread()
	}

	@Provides
	@RealmScheduler
	@Singleton
	fun provideRealmCoroutineDispatcher(@RealmScheduler scheduler: Scheduler): CoroutineDispatcher {
		return scheduler.asCoroutineDispatcher()
	}

	@Provides
	@Singleton
	fun provideRealm(application: Application): Realm {
		Realm.init(application)
		return Realm.getDefaultInstance()
	}
}
