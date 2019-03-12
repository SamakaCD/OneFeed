package com.ivansadovyi.domain.app

interface AppInteractor {

	suspend fun init()

	suspend fun performFirstLaunchSetup()
}
