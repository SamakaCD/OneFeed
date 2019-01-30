package com.ivansadovyi.domain

interface UseCase<R> {

	suspend fun execute(): R
}
