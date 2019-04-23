package com.ivansadovyi.onefeed.plugin.feedly;

import android.graphics.Bitmap;

import com.ivansadovyi.onefeed.plugin.feedly.api.ApiService;
import com.ivansadovyi.onefeed.plugin.feedly.api.responses.StreamContentsResponse;
import com.ivansadovyi.onefeed.plugin.feedly.di.DaggerFeedlyComponent;
import com.ivansadovyi.onefeed.plugin.feedly.di.FeedlyModule;
import com.ivansadovyi.onefeed.plugin.feedly.utils.MappingIterable;
import com.ivansadovyi.sdk.FeedItem;
import com.ivansadovyi.sdk.OneFeedPlugin;
import com.ivansadovyi.sdk.OneFeedPluginDescriptor;
import com.ivansadovyi.sdk.OneFeedPluginParams;
import com.ivansadovyi.sdk.auth.AuthorizationHandler;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class FeedlyPlugin extends OneFeedPlugin {

	public static final OneFeedPluginDescriptor DESCRIPTOR = new OneFeedPluginDescriptor.Builder()
			.setName("Feedly")
			.setClassName(FeedlyPlugin.class.getName())
			.setIconUri("")
			.build();

	@Inject
	AuthTokenRepository authTokenRepository;

	@Inject
	CollectionRepository collectionRepository;

	@Inject
	FeedlyAuthorizationHandler authorizationHandler;

	@Inject
	ApiService apiService;

	@Inject
	OneFeedItemFactory feedItemFactory;

	private String continuation;

	@Override
	public void onInit(@NonNull OneFeedPluginParams params) {
		super.onInit(params);
		injectDependencies();
		authTokenRepository.setAuthToken(params.getAuthorization());
	}

	@Override
	public void onAuthorizationStateChanged(@NonNull OneFeedPluginParams newParams) {
		super.onAuthorizationStateChanged(newParams);
		authTokenRepository.setAuthToken(newParams.getAuthorization());
	}

	@NonNull
	@Override
	public AuthorizationHandler getAuthorizationHandler() {
		return authorizationHandler;
	}

	@NonNull
	@Override
	public Bitmap getIcon() {
		return Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
	}

	@Override
	public Iterable<FeedItem> loadNextItems() throws Throwable {
		StreamContentsResponse streamContents = apiService.getStreamContents(
				authTokenRepository.getAuthToken(),
				collectionRepository.getDefaultCollection().getId(),
				continuation
		).execute().body();
		this.continuation = streamContents.getContinuation();
		return new MappingIterable<>(streamContents.getItems(), feedItemFactory::create);
	}

	@Override
	public void reset() {
		super.reset();
		this.continuation = null;
	}

	private void injectDependencies() {
		DaggerFeedlyComponent.builder()
				.feedlyModule(new FeedlyModule(ApiService.URL))
				.build()
				.inject(this);
	}
}
