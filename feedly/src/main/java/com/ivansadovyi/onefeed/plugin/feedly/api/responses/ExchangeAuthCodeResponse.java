package com.ivansadovyi.onefeed.plugin.feedly.api.responses;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class ExchangeAuthCodeResponse {

	@SerializedName("access_token")
	private String accessToken;

	@NonNull
	public String getAccessToken() {
		return accessToken;
	}
}
