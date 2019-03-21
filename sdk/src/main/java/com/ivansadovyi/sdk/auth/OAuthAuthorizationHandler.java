package com.ivansadovyi.sdk.auth;

import androidx.annotation.NonNull;

public interface OAuthAuthorizationHandler extends AuthorizationHandler {

	@NonNull
	OAuthParams onRequestOAuthParams();

	String processOAuthResponse(@NonNull String response);
}
