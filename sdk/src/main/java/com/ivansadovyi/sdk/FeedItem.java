package com.ivansadovyi.sdk;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;

public class FeedItem {

	public enum Priority {
		NONE,
		HIGH
	}

	private String id;
	private String title;
	private String content;

	@Nullable
	private Date publicationDate;

	@Nullable
	private String avatarImageUrl;
	private List<FeedImage> images;
	private Priority priority;
	private List<SubItem> subItems;

	private FeedItem() {

	}

	protected FeedItem(FeedItem source) {
		this.id = source.id;
		this.title = source.title;
		this.content = source.content;
		this.publicationDate = source.publicationDate;
		this.avatarImageUrl = source.avatarImageUrl;
		this.images = source.images;
		this.priority = source.priority;
		this.subItems = source.subItems;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	@Nullable
	public String getContent() {
		return content;
	}

	@Nullable
	public Date getPublicationDate() {
		return publicationDate;
	}

	@Nullable
	public String getAvatarImageUrl() {
		return avatarImageUrl;
	}

	public List<FeedImage> getImages() {
		return images;
	}

	public Priority getPriority() {
		return priority;
	}

	public List<SubItem> getSubItems() {
		return subItems;
	}

	public static class Builder {

		private String id;
		private String title;
		private String content;
		private Date publicationDate;
		private String avatarImageUrl;
		private List<FeedImage> images = Collections.emptyList();
		private Priority priority = Priority.NONE;
		private List<SubItem> subItems = Collections.emptyList();

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

		public Builder setPriority(Priority priority) {
			this.priority = priority;
			return this;
		}

		public Builder setSubItems(List<SubItem> subItems) {
			this.subItems = subItems;
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
			feedItem.priority = priority;
			feedItem.subItems = subItems;
			return feedItem;
		}
	}
}
