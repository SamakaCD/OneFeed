package com.ivansadovyi.domain.utils

import com.ivansadovyi.domain.Store
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

open class ObservableStore<T> : Store<T> {

	private val subject = PublishSubject.create<T>()

	override val observable: Observable<T>
		get() = subject

	fun notifyChange(change: T) {
		subject.onNext(change)
	}
}
