package com.ivansadovyi.truba.saga

import com.ivansadovyi.truba.Disposer

typealias Subscriber<T> = (T) -> Unit

class Subject<T> {

	private val subscribers = mutableSetOf<Subscriber<T>>()

	fun notify(data: T) {
		subscribers.forEach { it(data) }
	}

	fun subscribe(subscriber: Subscriber<T>): Disposer {
		subscribers.add(subscriber)
		return Disposer { subscribers.remove(subscriber) }
	}
}