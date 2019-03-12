package com.ivansadovyi.data.app

import android.app.Application
import android.content.Context
import com.ivansadovyi.domain.app.AppVersionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppVersionRepositoryImpl @Inject constructor(application: Application) : AppVersionRepository {

	private val preferences = application.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

	override suspend fun getPreviouslyInstalledAppVersion(): Int {
		return preferences.getInt(KEY_APP_VERSION, AppVersionRepository.FIRST_LAUNCH_VERSION)
	}

	companion object {
		private const val PREFERENCES_NAME = "app_version"
		private const val KEY_APP_VERSION = "app_version"
	}
}
