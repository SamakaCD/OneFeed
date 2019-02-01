package com.ivansadovyi.domain.plugin.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.plugin.DetailedRateLimitException
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.RateLimitException

abstract class PluginInvocationUseCase<T>(private val plugin: OneFeedPlugin) : UseCase<T> {

	protected abstract suspend fun executePluginInvocation(): T

	override suspend fun execute(): T {
		try {
			return executePluginInvocation()
		} catch (throwable: Throwable) {
			throw mapException(throwable)
		}
	}

	private fun mapException(throwable: Throwable): Throwable {
		return when (throwable) {
			is RateLimitException -> DetailedRateLimitException(plugin)
			else -> throwable
		}
	}
}
