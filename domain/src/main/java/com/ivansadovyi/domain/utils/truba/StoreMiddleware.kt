package com.ivansadovyi.domain.utils.truba

import com.ivansadovyi.domain.utils.ObservableStore
import com.ivansadovyi.truba.ActionDispatcher
import com.ivansadovyi.truba.Middleware

abstract class StoreMiddleware : ObservableStore<Any>(), Middleware<Any> {

	abstract fun onAction(action: Any): Boolean

	override fun onAction(action: Any, busDispatcher: ActionDispatcher<Any>, nextMiddlewareDispatcher: ActionDispatcher<Any>) {
		val wasStoreChanged = onAction(action)
		if (wasStoreChanged) {
			notifyChange(action)
		}

		nextMiddlewareDispatcher.dispatch(action)
	}
}
