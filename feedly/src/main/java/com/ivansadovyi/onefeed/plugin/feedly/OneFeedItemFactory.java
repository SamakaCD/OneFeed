package com.ivansadovyi.onefeed.plugin.feedly;

import com.ivansadovyi.sdk.FeedItem;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.Nullable;

@Singleton
public class OneFeedItemFactory {

	private CollectionRepository collectionRepository;

	@Inject
	public OneFeedItemFactory(CollectionRepository collectionRepository) {
		this.collectionRepository = collectionRepository;
	}

	public FeedItem create(FeedlyItem item) {
		return new FeedItem.Builder()
				.setId(item.getId())
				.setPublicationDate(new Date(item.getPublished()))
				.setAvatarImageUrl(findAvatarUrl(item))
				.setTitle(item.getOrigin().getTitle())
				.setContent(item.getContent().getContent())
				.build();
	}

	@Nullable
	private String findAvatarUrl(FeedlyItem item) {
		try {
			for (Feed feed : collectionRepository.getDefaultCollection().getFeeds()) {
				FeedlyItem.Origin origin = item.getOrigin();
				if (origin.getStreamId().equals(feed.getId())) {
					System.out.println("found avatar url " + origin.getTitle() + " " + feed.getVisualUrl());
					return feed.getVisualUrl();
				}
			}
			System.out.println("didnt found feed");

		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return null;
	}
}
