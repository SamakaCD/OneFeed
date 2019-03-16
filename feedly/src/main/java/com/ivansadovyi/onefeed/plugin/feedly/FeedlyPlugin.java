package com.ivansadovyi.onefeed.plugin.feedly;

import android.graphics.Bitmap;

import com.ivansadovyi.sdk.FeedItem;
import com.ivansadovyi.sdk.OneFeedPlugin;
import com.ivansadovyi.sdk.OneFeedPluginDescriptor;
import com.ivansadovyi.sdk.auth.AuthorizationHandler;

import java.util.Collections;

import androidx.annotation.NonNull;

public class FeedlyPlugin extends OneFeedPlugin {

	public static final OneFeedPluginDescriptor DESCRIPTOR = new OneFeedPluginDescriptor.Builder()
			.setName("Feedly")
			.setClassName(FeedlyPlugin.class.getName())
			.setIconUri("")
			.build();

	@NonNull
	@Override
	public AuthorizationHandler getAuthorizationHandler() {
		return FeedlyAuthorizationHandler.getInstance();
	}

	@NonNull
	@Override
	public Bitmap getIcon() {
		return Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
	}

	@Override
	public Iterable<FeedItem> loadNextItems() {
		return Collections.emptyList();
	}
}
