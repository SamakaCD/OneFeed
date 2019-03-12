package com.ivansadovyi.domain.app

interface AppVersionRepository {

	suspend fun getPreviouslyInstalledAppVersion(): Int

	suspend fun setAppVersion(version: Int)

	companion object {
		const val FIRST_LAUNCH_VERSION = 0
	}
}
