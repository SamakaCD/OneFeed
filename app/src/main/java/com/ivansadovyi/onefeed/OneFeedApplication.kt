package com.ivansadovyi.onefeed

import android.app.Activity
import android.app.Application
import com.ivansadovyi.domain.app.AppInteractor
import com.ivansadovyi.onefeed.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class OneFeedApplication : Application(), HasActivityInjector {

	@Inject
	lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

	@Inject
	lateinit var appInteractor: AppInteractor

	private val coroutineScope = CoroutineScope(Dispatchers.Main)

	override fun onCreate() {
		super.onCreate()
		setupDagger()
		invokeInit()
	}

	override fun activityInjector(): AndroidInjector<Activity> {
		return activityDispatchingAndroidInjector
	}

	private fun invokeInit() {
		coroutineScope.launch {
			appInteractor.init()
		}
	}

	private fun setupDagger() {
		DaggerAppComponent.builder()
				.application(this)
				.build()
				.inject(this)
	}
}
