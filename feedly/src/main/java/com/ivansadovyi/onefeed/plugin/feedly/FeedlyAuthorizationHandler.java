package com.ivansadovyi.onefeed.plugin.feedly;

import android.net.Uri;

import com.ivansadovyi.onefeed.plugin.feedly.api.ApiService;
import com.ivansadovyi.onefeed.plugin.feedly.api.requests.ExchangeAuthCodeRequest;
import com.ivansadovyi.sdk.auth.OAuthAuthorizationHandler;
import com.ivansadovyi.sdk.auth.OAuthParams;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;

@Singleton
public class FeedlyAuthorizationHandler implements OAuthAuthorizationHandler {

	private static final String CALLBACK_URL = "http://localhost";

	private final ApiService apiService;

	@Inject
	public FeedlyAuthorizationHandler(ApiService apiService) {
		this.apiService = apiService;
	}

	@NonNull
	@Override
	public OAuthParams onRequestOAuthParams() {
		return new OAuthParams.Builder()
				.setAuthUrl(getAuthUrl())
				.setCallbackUrl(CALLBACK_URL)
				.build();
	}

	@Override
	public String processOAuthResponse(@NonNull String response) {
		try {
			Uri responseUri = Uri.parse(response);
			String code = responseUri.getQueryParameter("code");
			ExchangeAuthCodeRequest exchangeRequest = new ExchangeAuthCodeRequest()
					.setClientId(ApiService.CLIENT_ID)
					.setClientSecret(ApiService.CLIENT_SECRET)
					.setCode(code)
					.setGrantType("authorization_code")
					.setRedirectUri(CALLBACK_URL);
			return apiService.exchangeAuthCode(exchangeRequest).execute().body().getAccessToken();
		} catch(Throwable throwable) {
		    throwable.printStackTrace();
		    return null;
		}
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
