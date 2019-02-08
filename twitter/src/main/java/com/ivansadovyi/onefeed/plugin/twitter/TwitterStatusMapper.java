package com.ivansadovyi.onefeed.plugin.twitter;

import com.ivansadovyi.sdk.FeedImage;
import com.ivansadovyi.sdk.FeedItem;

import java.util.ArrayList;
import java.util.List;

import twitter4j.MediaEntity;
import twitter4j.Status;

public class TwitterStatusMapper {

	private static final String MEDIA_TYPE_PHOTO = "photo";

	public static FeedItem toFeedItem(Status status) {
		return new FeedItem.Builder()
				.setId(String.valueOf(status.getId()))
				.setTitle(status.getUser().getName())
				.setContent(status.getText())
				.setPublicationDate(status.getCreatedAt())
				.setAvatarImageUrl(status.getUser().getBiggerProfileImageURLHttps())
				.setImages(getFeedImagesOfStatus(status))
				.build();
	}

	private static List<FeedImage> getFeedImagesOfStatus(Status status) {
		List<FeedImage> images = new ArrayList<>();
		MediaEntity[] mediaEntities = status.getMediaEntities();
		for (MediaEntity mediaEntity : mediaEntities) {
			if (mediaEntity.getType().equals(MEDIA_TYPE_PHOTO)) {
				MediaEntity.Size size = mediaEntity.getSizes().get(MediaEntity.Size.MEDIUM);
				FeedImage image = new FeedImage.Builder()
						.setId(String.valueOf(mediaEntity.getId()))
						.setUrl(mediaEntity.getMediaURLHttps())
						.setWidth(size.getWidth())
						.setHeight(size.getHeight())
						.build();
				images.add(image);
			}
		}
		return images;
	}
}

