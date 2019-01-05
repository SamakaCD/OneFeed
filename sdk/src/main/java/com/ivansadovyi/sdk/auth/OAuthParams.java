package com.ivansadovyi.sdk.auth;

import androidx.annotation.NonNull;

public class OAuthParams {

	private String authUrl;
	private String callbackUrl;

	private OAuthParams() {

	}

	@NonNull
	public String getAuthUrl() {
		return authUrl;
	}

	@NonNull
	public String getCallbackUrl() {
		return callbackUrl;
	}

	public static class Builder {

		private String authUrl;
		private String callbackUrl;

		public Builder setAuthUrl(@NonNull String authUrl) {
			this.authUrl = authUrl;
			return this;
		}

		public Builder setCallbackUrl(@NonNull String callbackUrl) {
			this.callbackUrl = callbackUrl;
			return this;
		}

		public OAuthParams build() {
			OAuthParams params = new OAuthParams();
			params.authUrl = authUrl;
			params.callbackUrl = callbackUrl;
			return params;
		}
	}
}
