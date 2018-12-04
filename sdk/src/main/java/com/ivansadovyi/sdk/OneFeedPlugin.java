package com.ivansadovyi.sdk;

import com.ivansadovyi.sdk.auth.AuthorizationHandler;
import com.ivansadovyi.sdk.auth.AuthorizationState;

import androidx.annotation.NonNull;
import io.reactivex.Completable;
import io.reactivex.Observable;

import static com.ivansadovyi.sdk.auth.AuthorizationState.AUTHORIZED;
import static com.ivansadovyi.sdk.auth.AuthorizationState.UNAUTHORIZED;

public abstract class OneFeedPlugin {

	private AuthorizationState authorizationState = UNAUTHORIZED;

	public void init(@NonNull OneFeedPluginInitParams params) {
		authorizationState = params.getAuthorization() != null ? AUTHORIZED : UNAUTHORIZED;
		onInit();
	}

	public abstract AuthorizationHandler getAuthorizationHandler();

	public AuthorizationState getAuthorizationState() {
		return authorizationState;
	}

	public void onInit() {

	}

	@NonNull
	public String handleAuthorizationResponse(String response) {
		throw new RuntimeException("Authorization response was not handled");
	}

	public abstract Observable<FeedItem> loadNextItems();

	public Completable reset() {
		return Completable.complete();
	}

	public Observable<FeedItem> refresh() {
		return reset().andThen(loadNextItems());
	}
}
