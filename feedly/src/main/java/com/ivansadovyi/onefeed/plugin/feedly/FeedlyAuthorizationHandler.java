package com.ivansadovyi.onefeed.plugin.feedly;

import android.net.Uri;

import com.ivansadovyi.sdk.auth.OAuthAuthorizationHandler;
import com.ivansadovyi.sdk.auth.OAuthParams;

import androidx.annotation.NonNull;

public class FeedlyAuthorizationHandler implements OAuthAuthorizationHandler {

	private static final String CALLBACK_URL = "http://localhost";

	private static FeedlyAuthorizationHandler instance;

	private FeedlyAuthorizationHandler() {

	}

	public static FeedlyAuthorizationHandler getInstance() {
		if (instance == null) {
			instance = new FeedlyAuthorizationHandler();
		}

		return instance;
	}

	@NonNull
	@Override
	public OAuthParams onRequestOAuthParams() {
		return new OAuthParams.Builder()
				.setAuthUrl(getAuthUrl())
				.setCallbackUrl(CALLBACK_URL)
				.build();
	}

	@NonNull
	@Override
	public String processOAuthResponse(@NonNull String response) {
		return null;
	}

	private String getAuthUrl() {
		return Uri.parse(ApiService.URL)
				.buildUpon()
				.appendEncodedPath("/v3/auth/auth")
				.appendQueryParameter("response_type", "code")
				.appendQueryParameter("client_id", ApiService.CLIENT_ID)
				.appendQueryParameter("redirect_uri", CALLBACK_URL)
				.appendQueryParameter("scope", "https://cloud.feedly.com/subscriptions")
				.build()
				.toString();
	}
}
