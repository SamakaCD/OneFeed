package com.ivansadovyi.onefeed.plugin.twitter;

import com.ivansadovyi.onefeed.plugin.twitter.utils.MappingIterable;
import com.ivansadovyi.sdk.FeedItem;
import com.ivansadovyi.sdk.OneFeedPlugin;
import com.ivansadovyi.sdk.OneFeedPluginDescriptor;
import com.ivansadovyi.sdk.OneFeedPluginParams;
import com.ivansadovyi.sdk.RateLimitException;
import com.ivansadovyi.sdk.auth.AuthorizationHandler;
import com.ivansadovyi.sdk.auth.AuthorizationState;

import java.util.List;

import androidx.annotation.NonNull;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterPlugin extends OneFeedPlugin {

	public static final OneFeedPluginDescriptor DESCRIPTOR = new OneFeedPluginDescriptor.Builder()
			.setName("Twitter")
			.setClassName(TwitterPlugin.class.getName())
			.setIconUri("")
			.build();

	private static final int NO_ID = -1;

	private long lastLoadedStatusId = NO_ID;

	@Override
	public void onInit(@NonNull OneFeedPluginParams params) {
		super.onInit(params);
		updateTwitterConfiguration();
	}

	@Override
	public void onAuthorizationStateChanged(@NonNull OneFeedPluginParams newParams) {
		super.onAuthorizationStateChanged(newParams);
		updateTwitterConfiguration();
	}

	@Override
	public AuthorizationHandler getAuthorizationHandler() {
		return TwitterAuthorizationHandler.getInstance();
	}

	@Override
	public Iterable<FeedItem> loadNextItems() throws Throwable {
		Twitter twitter = TwitterHolder.getInstance().getTwitter();
		List<Status> timeline;

		try {
			timeline = twitter.getHomeTimeline(getNextPaging());
		} catch(TwitterException exception) {
			if (exception.exceededRateLimitation()) {
				throw new RateLimitException();
			} else {
				throw exception;
			}
		}

		if (!timeline.isEmpty()) {
			Status lastStatus = timeline.get(timeline.size() - 1);
			lastLoadedStatusId = lastStatus.getId();
		}
		return new MappingIterable<>(timeline, TwitterStatusMapper::toFeedItem);
	}

	@Override
	public void reset() {
		super.reset();
		lastLoadedStatusId = NO_ID;
	}

	private Paging getNextPaging() {
		if (lastLoadedStatusId == NO_ID) {
			return new Paging();
		} else {
			return new Paging().maxId(lastLoadedStatusId);
		}
	}

	private void updateTwitterConfiguration() {
		ConfigurationBuilder builder = new ConfigurationBuilder()
				.setOAuthConsumerKey(BuildConfig.CONSUMER_KEY)
				.setOAuthConsumerSecret(BuildConfig.CONSUMER_SECRET);

		if (getAuthorizationState() == AuthorizationState.AUTHORIZED) {
			try {
				String serializedAccessToken = getParams().getAuthorization();
				AccessToken accessToken = AccessTokenSerializer.deserialize(serializedAccessToken);
				builder.setOAuthAccessToken(accessToken.getToken());
				builder.setOAuthAccessTokenSecret(accessToken.getTokenSecret());
			} catch (Throwable throwable) {
				throw new IllegalStateException("Cannot update authorization state.", throwable);
			}
		}

		TwitterHolder.init(builder.build());
	}
}
