package com.ivansadovyi.sdk;

import android.content.Context;
import android.graphics.Bitmap;

import com.ivansadovyi.sdk.auth.AuthorizationHandler;
import com.ivansadovyi.sdk.auth.AuthorizationState;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

public abstract class OneFeedPlugin {

	private OneFeedPluginParams params;

	@CallSuper
	public void onInit(@NonNull OneFeedPluginParams params) {
		this.params = params;
	}

	@CallSuper
	public void onAuthorizationStateChanged(@NonNull OneFeedPluginParams newParams) {
		params = newParams;
	}

	public abstract AuthorizationHandler getAuthorizationHandler();

	@NonNull
	public AuthorizationState getAuthorizationState() {
		return getParams().getAuthorizationState();
	}

	@NonNull
	public Context getContext() {
		return getParams().getContext();
	}

	@NonNull
	public OneFeedPluginDescriptor getDescriptor() {
		return getParams().getDescriptor();
	}

	@NonNull
	public abstract Bitmap getIcon();

	@NonNull
	public OneFeedPluginParams getParams() {
		return params;
	}

	public abstract Iterable<FeedItem> loadNextItems() throws Throwable;

	@CallSuper
	public void reset() {

	}

	public Iterable<FeedItem> refresh() throws Throwable {
		reset();
		return loadNextItems();
	}
}
