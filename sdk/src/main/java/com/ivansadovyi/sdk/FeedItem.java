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
}
