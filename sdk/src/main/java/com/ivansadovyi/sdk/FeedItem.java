package com.ivansadovyi.sdk;

import java.util.Date;

public class FeedItem {

	private String id;
	private String title;
	private String content;
	private Date publicationDate;
	private String avatarImageUrl;

	public FeedItem(String id, String title, String content, Date publicationDate, String avatarImageUrl) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.publicationDate = publicationDate;
		this.avatarImageUrl = avatarImageUrl;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public String getAvatarImageUrl() {
		return avatarImageUrl;
	}

	public static class Builder {

		private String id;
		private String title;
		private String content;
		private Date publicationDate;
		private String avatarImageUrl;

		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setContent(String content) {
			this.content = content;
			return this;
		}

		public Builder setPublicationDate(Date publicationDate) {
			this.publicationDate = publicationDate;
			return this;
		}

		public Builder setAvatarImageUrl(String avatarImageUrl) {
			this.avatarImageUrl = avatarImageUrl;
			return this;
		}

		public FeedItem build() {
			return new FeedItem(id, title, content, publicationDate, avatarImageUrl);
		}
	}
}
