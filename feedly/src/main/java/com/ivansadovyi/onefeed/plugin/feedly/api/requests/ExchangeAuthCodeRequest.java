package com.ivansadovyi.onefeed.plugin.feedly.api.requests;

import com.google.gson.annotations.SerializedName;

public class ExchangeAuthCodeRequest {

	private String code;

	@SerializedName("client_id")
	private String clientId;

	@SerializedName("client_secret")
	private String clientSecret;

	@SerializedName("redirect_uri")
	private String redirectUri;

	@SerializedName("grant_type")
	private String grantType;

	public ExchangeAuthCodeRequest setCode(String code) {
		this.code = code;
		return this;
	}

	public ExchangeAuthCodeRequest setClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}

	public ExchangeAuthCodeRequest setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
		return this;
	}

	public ExchangeAuthCodeRequest setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
		return this;
	}

	public ExchangeAuthCodeRequest setGrantType(String grantType) {
		this.grantType = grantType;
		return this;
	}
}
