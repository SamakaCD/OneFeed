package com.ivansadovyi.sdk;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OneFeedPluginDescriptor implements Serializable {

	private String name;
	private String className;
	private String iconUri;

	protected OneFeedPluginDescriptor() {

	}

	protected OneFeedPluginDescriptor(String name, String className, String iconUri) {
		this.name = name;
		this.className = className;
		this.iconUri = iconUri;
	}

	public String getName() {
		return name;
	}

	public String getClassName() {
		return className;
	}

	public String getIconUri() {
		return iconUri;
	}

	@Override
	public boolean equals(@Nullable Object obj) {
		if (!(obj instanceof OneFeedPluginDescriptor)) {
			return false;
		}
		return this == obj || this.className.equals(((OneFeedPluginDescriptor) obj).className);
	}

	@NonNull
	@Override
	public String toString() {
		return getName() + " (" + getClassName() + ")";
	}

	public static class Builder {

		private String className;
		private String iconUri;
		private String name;

		@NonNull
		public OneFeedPluginDescriptor build() {
			OneFeedPluginDescriptor descriptor = new OneFeedPluginDescriptor();
			descriptor.className = this.className;
			descriptor.iconUri = this.iconUri;
			descriptor.name = this.name;
			return descriptor;
		}

		public Builder setClassName(String className) {
			this.className = className;
			return this;
		}

		public Builder setIconUri(String iconUri) {
			this.iconUri = iconUri;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}
	}
}
