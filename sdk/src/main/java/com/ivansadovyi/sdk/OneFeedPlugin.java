package com.ivansadovyi.sdk;

import android.content.Context;

import com.ivansadovyi.sdk.auth.AuthorizationHandler;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

public abstract class OneFeedPlugin {

	private Context context;

	@CallSuper
	public void onInit(@NonNull OneFeedPluginInitParams params) {
		this.context = params.getContext();
	}

	public abstract AuthorizationHandler getAuthorizationHandler();

	@NonNull
	public Context getContext() {
		return context;
	}

	public abstract Iterable<FeedItem> loadNextItems();

	@CallSuper
	public void reset() {

	}

	public Iterable<FeedItem> refresh() {
		reset();
		return loadNextItems();
	}
}
