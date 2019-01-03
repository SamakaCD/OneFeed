package com.ivansadovyi.onefeed.plugin.twitter

import com.ivansadovyi.sdk.auth.OAuthAuthorizationHandler
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

class TwitterAuthorizationHandler : OAuthAuthorizationHandler {

	override fun onRequestAuthorizationUrl(): String {
		val configuration = ConfigurationBuilder()
				.setOAuthConsumerKey(BuildConfig.CONSUMER_KEY)
				.setOAuthConsumerSecret(BuildConfig.CONSUMER_SECRET)
				.build()

		val twitter = TwitterFactory(configuration).instance
		return twitter.getOAuthRequestToken(BuildConfig.CALLBACK_URL).authenticationURL
	}
}
