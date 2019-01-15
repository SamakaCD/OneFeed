package com.ivansadovyi.domain.utils

import io.reactivex.Scheduler
import io.reactivex.Single
import kotlinx.coroutines.rx2.await

suspend fun <T> Single<T>.await(subscribeOn: Scheduler) = subscribeOn(subscribeOn).await()
