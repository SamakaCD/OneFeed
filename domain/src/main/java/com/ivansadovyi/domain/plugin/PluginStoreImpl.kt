package com.ivansadovyi.domain.plugin

import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.domain.plugin.auth.AuthorizationResponseProcessorFactory
import com.ivansadovyi.domain.plugin.auth.UnsupportedPluginAuthorizationMethodException
import com.ivansadovyi.domain.plugin.descriptor.PluginDescriptorStore
import com.ivansadovyi.domain.utils.ObservableStore
import com.ivansadovyi.domain.utils.ObservableValue
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.OneFeedPluginParams
import com.ivansadovyi.sdk.auth.AuthorizationParams
import com.ivansadovyi.sdk.auth.AuthorizationState
import com.ivansadovyi.sdk.auth.OAuthAuthorizationHandler
import dagger.Lazy
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginStoreImpl @Inject constructor(
		private val pluginDescriptorStore: Lazy<PluginDescriptorStore>,
		private val feedItemsStore: Lazy<FeedItemsStore>
) : ObservableStore<PluginStore>(), PluginStore {

	override var plugins: MutableList<OneFeedPlugin> by ObservableValue(defaultValue = mutableListOf())

	private val authorizationParamsCache = mutableMapOf<OneFeedPlugin, AuthorizationParams>()

	override fun startAuthorization(pluginDescriptor: OneFeedPluginDescriptor): Single<AuthorizationParams> {
		return Single.fromCallable<AuthorizationParams> {
			val pluginParams = OneFeedPluginParams.Builder()
					.setAuthorizationState(AuthorizationState.AUTHORIZING)
					.setDescriptor(pluginDescriptor)
					.build()

			val plugin = pluginDescriptorStore.get().instantiatePlugin(pluginDescriptor, pluginParams).blockingGet()
			val authorizationHandler = plugin.authorizationHandler
			val authParams = when (authorizationHandler) {
				is OAuthAuthorizationHandler -> authorizationHandler.onRequestOAuthParams()
				else -> throw UnsupportedPluginAuthorizationMethodException()
			}

			authorizationParamsCache[plugin] = authParams
			plugins.add(plugin)
			return@fromCallable authParams
		}.subscribeOn(Schedulers.io())
	}

	override fun processAuthorizationResponse(pluginDescriptor: OneFeedPluginDescriptor, response: String): Single<Boolean> {
		return Single.fromCallable {
			val authorizingPlugin = getAuthorizingPlugin(pluginDescriptor)
			val authorizationHandler = authorizingPlugin.authorizationHandler
			val responseProcessor = AuthorizationResponseProcessorFactory.create(authorizationHandler)
			val authParams = authorizationParamsCache[authorizingPlugin]
					?: throw IllegalStateException("Cannot retrieve cached auth params for plugin " +
							"with descriptor [$pluginDescriptor]")

			val result = responseProcessor.process(response, authParams, authorizationHandler)
			if (!result.wasProcessed || result.authorization == null) {
				return@fromCallable false
			}

			val newParams = authorizingPlugin.params.newBuilder()
					.setAuthorization(result.authorization)
					.setAuthorizationState(AuthorizationState.AUTHORIZED)
					.build()

			authorizingPlugin.onAuthorizationStateChanged(newParams)

			// Contents of store have been changed - need notify about this.
			notifyChange()

			// We have got new feed items source, so refresh feed.
			feedItemsStore.get().refresh()

			return@fromCallable true
		}.subscribeOn(Schedulers.io())
	}

	private fun getAuthorizingPlugin(descriptor: OneFeedPluginDescriptor): OneFeedPlugin {
		val instance = plugins.asSequence()
				.filter { it.authorizationState == AuthorizationState.AUTHORIZING }
				.filter { it.descriptor == descriptor }
				.firstOrNull()

		return instance
				?: throw IllegalStateException("Cannot find authorizing plugin instance with descriptor [$descriptor]")
	}
}
