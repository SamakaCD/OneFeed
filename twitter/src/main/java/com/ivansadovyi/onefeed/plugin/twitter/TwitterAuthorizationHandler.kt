package com.ivansadovyi.onefeed.plugin.twitter

import android.net.Uri
import com.ivansadovyi.sdk.auth.OAuthAuthorizationHandler
import com.ivansadovyi.sdk.auth.OAuthParams
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

class TwitterAuthorizationHandler : OAuthAuthorizationHandler {

	private var twitter: Twitter

	init {
		val configuration = ConfigurationBuilder()
				.setOAuthConsumerKey(BuildConfig.CONSUMER_KEY)
				.setOAuthConsumerSecret(BuildConfig.CONSUMER_SECRET)
				.build()

		twitter = TwitterFactory(configuration).instance
	}

	override fun onRequestOAuthParams(): OAuthParams {
		val authUrl = twitter.getOAuthRequestToken(BuildConfig.CALLBACK_URL).authenticationURL
		return OAuthParams.Builder()
				.setAuthUrl(authUrl)
				.setCallbackUrl(BuildConfig.CALLBACK_URL)
				.build()
	}

	override fun processOAuthResponse(response: String): String {
		val uri = Uri.parse(response)
		val oauthToken = uri.getQueryParameter(URL_OAUTH_TOKEN)
		val verifier = uri.getQueryParameter(URL_OAUTH_VERIFIER)
		val accessToken = twitter.getOAuthAccessToken(oauthToken, verifier)
		return AccessTokenSerializer.serialize(accessToken)
	}

	companion object {
		private const val URL_OAUTH_TOKEN = "oauth_token"
		private const val URL_OAUTH_VERIFIER = "oauth_verifier"
	}
}
