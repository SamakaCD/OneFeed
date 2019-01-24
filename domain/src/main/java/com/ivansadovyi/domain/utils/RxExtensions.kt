package com.ivansadovyi.domain.utils

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

fun <T> Observable<T>.filterAsync(asyncPredicate: (T) -> Single<Boolean>): Observable<T> {
	return flatMapMaybe { value ->
		asyncPredicate(value).flatMapMaybe {
			if (it) Maybe.just(value) else Maybe.empty()
		}
	}
}