package com.ivansadovyi.onefeed.plugin.twitter;

import com.ivansadovyi.sdk.FeedItem;

import twitter4j.Status;

public class TwitterStatusMapper {

	public static FeedItem toFeedItem(Status status) {
		return new FeedItem.Builder()
				.setId(String.valueOf(status.getId()))
				.setTitle(status.getUser().getName())
				.setContent(status.getText())
				.setPublicationDate(status.getCreatedAt())
				.setAvatarImageUrl(status.getUser().getProfileImageURL())
				.build();
	}
}

