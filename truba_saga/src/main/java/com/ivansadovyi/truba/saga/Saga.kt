package com.ivansadovyi.truba.saga

import com.ivansadovyi.truba.ActionDispatcher
import com.ivansadovyi.truba.Disposer
import com.ivansadovyi.truba.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

open class Saga<Action>(private val dispatcher: ActionDispatcher<Action>) : Middleware<Action> {

	private val actionSubject = Subject<Action>()
	private val coroutineScope = CoroutineScope(Dispatchers.Default)

	fun dispatch(action: Action) {
		dispatcher.dispatch(action)
	}

	inline fun <reified T : Action> bindAction(crossinline onAction: suspend () -> Unit) {
		bindAction(
				predicate = { it is T },
				onAction = { onAction() }
		)
	}

	inline fun <reified T : Action> bindAction(crossinline onAction: suspend (T) -> Unit) {
		bindAction(
				predicate = { it is T },
				onAction = { onAction(it as T) }
		)
	}

	fun bindAction(predicate: (Action) -> Boolean, onAction: suspend (Action) -> Unit): Disposer {
		val job = coroutineScope.launch {
			while (true) {
				val action = waitAction(predicate)
				onAction(action)
			}
		}
		return Disposer { job.cancel() }
	}

	suspend inline fun <reified T : Action> waitAction(): T {
		return waitAction { it is T } as T
	}

	suspend fun waitAction(predicate: (Action) -> Boolean): Action {
		return suspendCoroutine { continuation ->
			lateinit var disposer: Disposer
			disposer = actionSubject.subscribe { action ->
				if (predicate(action)) {
					disposer.dispose()
					continuation.resume(action)
				}
			}
		}
	}

	override fun onAction(action: Action, busDispatcher: ActionDispatcher<Action>, nextMiddlewareDispatcher: ActionDispatcher<Action>) {
		actionSubject.notify(action)
		nextMiddlewareDispatcher.dispatch(action)
	}
}
