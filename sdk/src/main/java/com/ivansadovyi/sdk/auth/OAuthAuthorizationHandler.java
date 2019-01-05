package com.ivansadovyi.sdk.auth;

import androidx.annotation.NonNull;

public interface OAuthAuthorizationHandler extends AuthorizationHandler {

	@NonNull
	OAuthParams onRequestOAuthParams();

	@NonNull
	String processOAuthResponse(@NonNull String response);
}
