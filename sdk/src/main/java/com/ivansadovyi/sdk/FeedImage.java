package com.ivansadovyi.sdk;

public class FeedImage {

	private String id;
	private String url;
	private int width;
	private int height;

	private FeedImage() {

	}

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static class Builder {

		private String id;
		private String url;
		private int width;
		private int height;

		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder setWidth(int width) {
			this.width = width;
			return this;
		}

		public Builder setHeight(int height) {
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
