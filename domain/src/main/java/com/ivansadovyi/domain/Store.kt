package com.ivansadovyi.domain

import io.reactivex.Observable

interface Store<T> {

	val observable: Observable<T>

}
