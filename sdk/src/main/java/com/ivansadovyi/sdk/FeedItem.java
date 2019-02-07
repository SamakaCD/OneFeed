package com.ivansadovyi.sdk;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FeedItem {

	private String id;
	private String title;
	private String content;
	private Date publicationDate;
	private String avatarImageUrl;
	private List<FeedImage> images = Collections.emptyList();

	private FeedItem() {

	}

	protected FeedItem(FeedItem source) {
		this.id = source.id;
		this.title = source.title;
		this.content = source.content;
		this.publicationDate = source.publicationDate;
		this.avatarImageUrl = source.avatarImageUrl;
		this.images = source.images;
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

	public List<FeedImage> getImages() {
		return images;
	}

	public static class Builder {

		private String id;
		private String title;
		private String content;
		private Date publicationDate;
		private String avatarImageUrl;
		private List<FeedImage> images;

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

		public Builder setImages(List<FeedImage> images) {
			this.images = images;
			return this;
		}

		public FeedItem build() {
			FeedItem feedItem = new FeedItem();
			feedItem.id = id;
			feedItem.title = title;
			feedItem.content = content;
			feedItem.publicationDate = publicationDate;
			feedItem.avatarImageUrl = avatarImageUrl;
			feedItem.images = images;
			return feedItem;
		}
	}
}
