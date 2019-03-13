package com.ivansadovyi.sdk;

import android.content.Context;

import com.ivansadovyi.sdk.auth.AuthorizationState;

import androidx.annotation.Nullable;

public class OneFeedPluginParams {

	private String authorization;
	private AuthorizationState authorizationState;
	private Context context;
	private OneFeedPluginDescriptor descriptor;
	private OneFeedHost host;

	private OneFeedPluginParams() {

	}

	@Nullable
	public String getAuthorization() {
		return authorization;
	}

	public AuthorizationState getAuthorizationState() {
		return authorizationState;
	}

	public Context getContext() {
		return context;
	}

	public OneFeedPluginDescriptor getDescriptor() {
		return descriptor;
	}

	public OneFeedHost getHost() {
		return host;
	}

	public Builder newBuilder() {
		return new Builder()
				.setAuthorization(getAuthorization())
				.setAuthorizationState(getAuthorizationState())
				.setContext(getContext())
				.setDescriptor(getDescriptor())
				.setHost(getHost());
	}

	public static class Builder {

		private String authorization;
		private AuthorizationState authorizationState;
		private Context context;
		private OneFeedPluginDescriptor descriptor;
		private OneFeedHost host;

		public Builder setAuthorization(@Nullable String authorization) {
			this.authorization = authorization;
			return this;
		}

		public Builder setAuthorizationState(AuthorizationState authorizationState) {
			this.authorizationState = authorizationState;
			return this;
		}

		public Builder setContext(Context context) {
			this.context = context;
			return this;
		}

		public Builder setDescriptor(OneFeedPluginDescriptor descriptor) {
			this.descriptor = descriptor;
			return this;
		}

		public Builder setHost(OneFeedHost host) {
			this.host = host;
			return this;
		}

		public OneFeedPluginParams build() {
			OneFeedPluginParams params = new OneFeedPluginParams();
			params.authorization = authorization;
			params.authorizationState = authorizationState;
			params.context = context;
			params.descriptor = descriptor;
			params.host = host;
			return params;
		}
	}
}
