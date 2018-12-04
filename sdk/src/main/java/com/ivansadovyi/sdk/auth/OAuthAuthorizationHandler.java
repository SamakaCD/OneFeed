package com.ivansadovyi.sdk.auth;

public interface OAuthAuthorizationHandler extends AuthorizationHandler {

	String getAuthorizationUrl();

	String getAuthorizationFromResponse(String response);
}
