package com.ivansadovyi.sdk;

import androidx.annotation.Nullable;

public class FeedImage {

	private String id;
	private String url;
	private Integer width;
	private Integer height;

	private FeedImage() {

	}

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	@Nullable
	public Integer getWidth() {
		return width;
	}

	@Nullable
	public Integer getHeight() {
		return height;
	}

	public static class Builder {

		private String id;
		private String url;
		private Integer width;
		private Integer height;

		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder setWidth(@Nullable Integer width) {
			this.width = width;
			return this;
		}

		public Builder setHeight(@Nullable Integer height) {
			this.height = height;
			return this;
		}

		public FeedImage build() {
			FeedImage feedImage = new FeedImage();
			feedImage.id = id;
			feedImage.url = url;
			feedImage.width = width;
			feedImage.height = height;
			return feedImage;
		}
	}
}
