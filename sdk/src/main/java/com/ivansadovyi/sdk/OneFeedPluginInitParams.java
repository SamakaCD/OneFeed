package com.ivansadovyi.sdk;

import android.content.Context;

public class OneFeedPluginInitParams {

	private String authorization;
	private Context context;

	private OneFeedPluginInitParams() {

	}

	public String getAuthorization() {
		return authorization;
	}

	public Context getContext() {
		return context;
	}

	public static class Builder {

		private String authorization;
		private Context context;

		public Builder setAuthorization(String authorization) {
			this.authorization = authorization;
			return this;
		}

		public Builder setContext(Context context) {
			this.context = context;
			return this;
		}

		public OneFeedPluginInitParams build() {
			OneFeedPluginInitParams params = new OneFeedPluginInitParams();
			params.authorization = authorization;
			params.context = context;
			return params;
		}
	}
}
