package com.ivansadovyi.onefeed.plugin.twitter;

import android.net.Uri;

import com.ivansadovyi.sdk.auth.OAuthAuthorizationHandler;
import com.ivansadovyi.sdk.auth.OAuthParams;

import androidx.annotation.NonNull;
import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterAuthorizationHandler implements OAuthAuthorizationHandler {

	private static final String URL_OAUTH_VERIFIER = "oauth_verifier";
	private static TwitterAuthorizationHandler instance;
	private RequestToken requestToken;

	private TwitterAuthorizationHandler() {

	}

	public static TwitterAuthorizationHandler getInstance() {
		if (instance == null) {
			instance = new TwitterAuthorizationHandler();
		}

		return instance;
	}

	@NonNull
	@Override
	public OAuthParams onRequestOAuthParams() {
		try {
			Twitter twitter = TwitterHolder.getInstance().getTwitter();
			requestToken = twitter.getOAuthRequestToken(BuildConfig.CALLBACK_URL);
			return new OAuthParams.Builder()
					.setAuthUrl(requestToken.getAuthenticationURL())
					.setCallbackUrl(BuildConfig.CALLBACK_URL)
					.build();
		} catch (Throwable throwable) {
			throw new RuntimeException("Cannot retrieve OAuth params.", throwable);
		}
	}

	@NonNull
	@Override
	public String processOAuthResponse(@NonNull String response) {
		try {
			Twitter twitter = TwitterHolder.getInstance().getTwitter();
			Uri uri = Uri.parse(response);
			String verifier = uri.getQueryParameter(URL_OAUTH_VERIFIER);
			AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
			return AccessTokenSerializer.serialize(accessToken);
		} catch (Throwable throwable) {
			throw new RuntimeException("Cannot process OAuth response.", throwable);
		}
	}
}
