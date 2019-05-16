package com.ivansadovyi.sdk;

import androidx.annotation.Nullable;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FeedItem {

	private String id;
	private String title;
	private String content;

	@Nullable
	private Date publicationDate;
	private boolean isDateVisible;

	@Nullable
	private String avatarImageUrl;
	private List<FeedImage> images;
	private List<SubItem> subItems;
	private boolean hasDetails;
	private boolean isLikeable;
	private boolean isLiked;
	private int likesCount;

	private FeedItem() {

	}

	protected FeedItem(FeedItem source) {
		this.id = source.id;
		this.title = source.title;
		this.content = source.content;
		this.publicationDate = source.publicationDate;
		this.isDateVisible = source.isDateVisible;
		this.avatarImageUrl = source.avatarImageUrl;
		this.images = source.images;
		this.subItems = source.subItems;
		this.hasDetails = source.hasDetails;
		this.isLikeable = source.isLikeable;
		this.isLiked = source.isLiked;
		this.likesCount = source.likesCount;
	}

	public Builder getBuilder() {
		return new Builder()
				.setId(getId())
				.setTitle(getTitle())
				.setContent(getContent())
				.setPublicationDate(getPublicationDate())
				.setDateVisible(isDateVisible())
				.setAvatarImageUrl(getAvatarImageUrl())
				.setImages(getImages())
				.setSubItems(getSubItems())
				.setHasDetails(isHasDetails())
				.setLikeable(isLikeable())
				.setLiked(isLiked())
				.setLikesCount(getLikesCount());
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

	public boolean isDateVisible() {
		return isDateVisible;
	}

	@Nullable
	public String getAvatarImageUrl() {
		return avatarImageUrl;
	}

	public List<FeedImage> getImages() {
		return images;
	}

	public List<SubItem> getSubItems() {
		return subItems;
	}

	public boolean isLikeable() {
		return isLikeable;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public int getLikesCount() {
		return likesCount;
	}

	public boolean isHasDetails() {
		return hasDetails;
	}

	public static class Builder {

		private String id;
		private String title;
		private String content;
		private Date publicationDate;
		private boolean isDateVisible = true;
		private String avatarImageUrl;
		private List<FeedImage> images = Collections.emptyList();
		private List<SubItem> subItems = Collections.emptyList();
		private boolean hasDetails = true;
		private boolean isLikeable;
		private boolean isLiked;
		private int likesCount;

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

		public Builder setDateVisible(boolean dateVisible) {
			isDateVisible = dateVisible;
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

		public Builder setSubItems(List<SubItem> subItems) {
			this.subItems = subItems;
			return this;
		}

		public Builder setHasDetails(boolean hasDetails) {
			this.hasDetails = hasDetails;
			return this;
		}

		public Builder setLikeable(boolean likeable) {
			isLikeable = likeable;
			return this;
		}

		public Builder setLiked(boolean liked) {
			isLiked = liked;
			return this;
		}

		public Builder setLikesCount(int likesCount) {
			this.likesCount = likesCount;
			return this;
		}

		public FeedItem build() {
			FeedItem feedItem = new FeedItem();
			feedItem.id = id;
			feedItem.title = title;
			feedItem.content = content;
			feedItem.publicationDate = publicationDate;
			feedItem.isDateVisible = isDateVisible;
			feedItem.avatarImageUrl = avatarImageUrl;
			feedItem.images = images;
			feedItem.subItems = subItems;
			feedItem.hasDetails = hasDetails;
			feedItem.isLikeable = isLikeable;
			feedItem.isLiked = isLiked;
			feedItem.likesCount = likesCount;
			return feedItem;
		}
	}
}
