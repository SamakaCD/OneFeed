package com.ivansadovyi.sdk.auth;

public interface OAuthAuthorizationHandler extends AuthorizationHandler {

	String onRequestAuthorizationUrl();
}
