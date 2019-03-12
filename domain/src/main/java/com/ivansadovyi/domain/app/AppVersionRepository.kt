package com.ivansadovyi.domain.app

interface AppVersionRepository {

	suspend fun getPreviouslyInstalledAppVersion(): Int

	companion object {
		const val FIRST_LAUNCH_VERSION = 0
	}
}
