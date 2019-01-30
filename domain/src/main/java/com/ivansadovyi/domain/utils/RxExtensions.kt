package com.ivansadovyi.domain.utils

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import java.util.concurrent.Callable

fun <T> singleFromCallable(scheduler: Scheduler, callable: Callable<T>): Single<T> {
	return Single.fromCallable(callable).subscribeOn(scheduler)
}

fun <T> Observable<T>.filterAsync(asyncPredicate: (T) -> Single<Boolean>): Observable<T> {
	return flatMapMaybe { value ->
		asyncPredicate(value).flatMapMaybe {
			if (it) Maybe.just(value) else Maybe.empty()
		}
	}
}