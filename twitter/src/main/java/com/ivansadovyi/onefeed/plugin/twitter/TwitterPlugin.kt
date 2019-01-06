package com.ivansadovyi.onefeed.plugin.twitter

import com.ivansadovyi.sdk.FeedItem
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.OneFeedPluginParams
import com.ivansadovyi.sdk.auth.AuthorizationHandler
import com.ivansadovyi.sdk.auth.AuthorizationState
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

class TwitterPlugin : OneFeedPlugin() {

	lateinit var twitter: Twitter

	override fun onInit(params: OneFeedPluginParams) {
		super.onInit(params)
		instantiateTwitter()
	}

	override fun onAuthorizationStateChanged(newParams: OneFeedPluginParams) {
		super.onAuthorizationStateChanged(newParams)
		instantiateTwitter()
	}

	override fun getAuthorizationHandler(): AuthorizationHandler {
		return TwitterAuthorizationHandler()
	}

	override fun loadNextItems(): Iterable<FeedItem> {
		return twitter.homeTimeline.map(TwitterStatusMapper::toFeedItem)
	}

	private fun instantiateTwitter() {
		val configurationBuilder = ConfigurationBuilder()
				.setOAuthConsumerKey(BuildConfig.CONSUMER_KEY)
				.setOAuthConsumerSecret(BuildConfig.CONSUMER_SECRET)

		if (authorizationState == AuthorizationState.AUTHORIZED) {
			val authToken = AccessTokenSerializer.deserialize(params.authorization!!)
			configurationBuilder.setOAuthAccessToken(authToken.token)
			configurationBuilder.setOAuthAccessTokenSecret(authToken.tokenSecret)
		}

		val configuration = configurationBuilder.build()
		twitter = TwitterFactory(configuration).instance
	}

	companion object {

		val DESCRIPTOR = OneFeedPluginDescriptor.Builder()
				.setName("Twitter")
				.setClassName(TwitterPlugin::javaClass.name)
				.build()
	}
}