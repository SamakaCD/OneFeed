package com.ivansadovyi.onefeed.plugin.feedly;

public class FeedlyItem {

	private String id;
	private Origin origin;
	private Content content;
	private long published;

	public String getId() {
		return id;
	}

	public Origin getOrigin() {
		return origin;
	}

	public Content getContent() {
		return content;
	}

	public long getPublished() {
		return published;
	}

	public static class Content {

		private String content;

		public String getContent() {
			return content;
		}
	}

	public static class Origin {

		private String streamId;
		private String title;

		public String getStreamId() {
			return streamId;
		}

		public String getTitle() {
			return title;
		}
	}
}
