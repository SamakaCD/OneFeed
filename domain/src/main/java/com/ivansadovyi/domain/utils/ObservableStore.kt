package com.ivansadovyi.domain.utils

import com.ivansadovyi.domain.Store
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

open class ObservableStore<T> : Store<T> {

	private val subject = BehaviorSubject.createDefault(this as T)

	override val observable: Observable<T>
		get() = subject

	@Suppress("UNCHECKED_CAST")
	fun notifyChange() {
		subject.onNext(this as T)
	}
}
