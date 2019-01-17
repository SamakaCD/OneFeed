package com.ivansadovyi.onefeed.plugin.twitter;

import com.ivansadovyi.onefeed.plugin.twitter.utils.MappingIterable;
import com.ivansadovyi.sdk.FeedItem;
import com.ivansadovyi.sdk.OneFeedPlugin;
import com.ivansadovyi.sdk.OneFeedPluginParams;
import com.ivansadovyi.sdk.auth.AuthorizationHandler;
import com.ivansadovyi.sdk.auth.AuthorizationState;

import androidx.annotation.NonNull;
import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterPlugin extends OneFeedPlugin {

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
		return new MappingIterable<>(twitter.getHomeTimeline(), TwitterStatusMapper::toFeedItem);
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
