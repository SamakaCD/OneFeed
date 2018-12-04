package com.ivansadovyi.sdk;

public class OneFeedPluginInitParams {

	private String authorization;

	private OneFeedPluginInitParams() {

	}

	public String getAuthorization() {
		return authorization;
	}

	public static class Builder {

		private String authorization;

		public void setAuthorization(String authorization) {
			this.authorization = authorization;
		}

		public OneFeedPluginInitParams build() {
			OneFeedPluginInitParams params = new OneFeedPluginInitParams();
			params.authorization = authorization;
			return params;
		}
	}
}

