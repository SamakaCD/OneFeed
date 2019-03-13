package com.ivansadovyi.onefeed

import android.app.Activity
import android.app.Application
import com.ivansadovyi.onefeed.di.AppModule
import com.ivansadovyi.onefeed.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class OneFeedApplication : Application(), HasActivityInjector {

	@Inject
	lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

	override fun onCreate() {
		super.onCreate()
		setupDagger()
	}

	override fun activityInjector(): AndroidInjector<Activity> {
		return activityDispatchingAndroidInjector
	}

	private fun setupDagger() {
		DaggerAppComponent.builder()
				.application(this)
				.appModule(AppModule(BuildConfig.VERSION_CODE))
				.build()
				.inject(this)
	}
}
