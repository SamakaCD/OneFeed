package com.ivansadovyi.domain.plugin.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.plugin.RateLimitException
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.RateLimitException as SdkRateLimitException

open class PluginInvocationUseCase<T> : UseCase<T> {

	private var plugin: OneFeedPlugin? = null

	constructor()

	constructor(plugin: OneFeedPlugin) {
		this.plugin = plugin
	}

	protected open suspend fun executePluginInvocation(): T {
		throw NotImplementedError()
	}

	override suspend fun execute(): T {
		val plugin = this.plugin ?: throw IllegalStateException("Plugin instance was not provided.")
		return pluginInvocation(plugin, ::executePluginInvocation)
	}

	protected suspend fun pluginInvocation(plugin: OneFeedPlugin, body: suspend () -> T): T {
		try {
			return body()
		} catch (throwable: Throwable) {
			throw mapException(plugin, throwable)
		}
	}

	private fun mapException(plugin: OneFeedPlugin, throwable: Throwable): Throwable {
		return when (throwable) {
			is SdkRateLimitException -> RateLimitException(plugin)
			else -> throwable
		}
	}
}
