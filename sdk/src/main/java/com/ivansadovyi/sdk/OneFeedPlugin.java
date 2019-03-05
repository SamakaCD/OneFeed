package com.ivansadovyi.sdk;

import android.content.Context;
import android.graphics.Bitmap;

import com.ivansadovyi.sdk.auth.AuthorizationHandler;
import com.ivansadovyi.sdk.auth.AuthorizationState;
import com.ivansadovyi.sdk.auth.EmptyAuthorizationHandler;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

public abstract class OneFeedPlugin {

	public interface OnSubItemClickListener {

		void onSubItemClick(@NonNull SubItem subItem, @NonNull FeedItem feedItem);
	}

	private OneFeedPluginParams params;

	@CallSuper
	public void onInit(@NonNull OneFeedPluginParams params) {
		this.params = params;
	}

	@CallSuper
	public void onAuthorizationStateChanged(@NonNull OneFeedPluginParams newParams) {
		params = newParams;
	}

	@NonNull
	public AuthorizationHandler getAuthorizationHandler() {
		return new EmptyAuthorizationHandler();
	}

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
	public OneFeedHost getHost() {
		return getParams().getHost();
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
