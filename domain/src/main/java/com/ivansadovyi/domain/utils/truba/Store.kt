package com.ivansadovyi.domain.utils.truba

import com.ivansadovyi.domain.utils.Consumer
import com.ivansadovyi.domain.utils.Disposable

abstract class Store<A : Action> {

	private val observers = mutableSetOf<Consumer<A>>()

	fun observe(observer: Consumer<A>): Disposable {
		observers.add(observer)
		return { observers.remove(observer) }
	}

	protected fun notifyChange(change: A) {
		observers.forEach { observer ->
			observer.invoke(change)
		}
	}
}
