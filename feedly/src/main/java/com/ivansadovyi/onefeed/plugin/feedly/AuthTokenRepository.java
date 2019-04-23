package com.ivansadovyi.onefeed.plugin.feedly;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AuthTokenRepository {

	private String authToken;

	@Inject
	public AuthTokenRepository() {

	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
}
