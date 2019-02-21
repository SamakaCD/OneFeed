package com.ivansadovyi.domain.log

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoggingInteractor @Inject constructor() {

	fun debug(message: String) {
		Log.d(TAG, message)
	}

	fun debug(at: Any, message: String) {
		debug("[${at.javaClass.simpleName}] $message")
	}

	fun warning(message: String) {
		Log.w(TAG, message)
	}

	fun warning(at: Any, message: String) {
		warning("[${at.javaClass.simpleName}] $message")
	}

	companion object {
		private const val TAG = "LoggingInteractor"
	}
}
