package com.ivansadovyi.sdk;

import android.support.annotation.Nullable;

import io.reactivex.Observable;

public abstract class OneFeedPlugin {

	public abstract Observable<FeedItem> loadNextItems(@Nullable String lastItemId);
}
