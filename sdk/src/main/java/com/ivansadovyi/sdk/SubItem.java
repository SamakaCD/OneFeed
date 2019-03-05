package com.ivansadovyi.sdk;

import android.graphics.Bitmap;

public class SubItem {

	private String id;
	private String title;
	private String description;
	private Bitmap icon;
	private String iconUrl;
	private Integer iconColor;

	private SubItem() {

	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Bitmap getIcon() {
		return icon;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public Integer getIconColor() {
		return iconColor;
	}

	public static class Builder {

		private String id;
		private String title;
		private String description;
		private Bitmap icon;
		private String iconUrl;
		private Integer iconColor;

		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder setIcon(Bitmap icon) {
			this.icon = icon;
			return this;
		}

		public Builder setIconUrl(String iconUrl) {
			this.iconUrl = iconUrl;
			return this;
		}

		public Builder setIconColor(Integer iconColor) {
			this.iconColor = iconColor;
			return this;
		}

		public SubItem build() {
			SubItem subItem = new SubItem();
			subItem.id = id;
			subItem.title = title;
			subItem.description = description;
			subItem.icon = icon;
			subItem.iconUrl = iconUrl;
			subItem.iconColor = iconColor;
			return subItem;
		}
	}
}
