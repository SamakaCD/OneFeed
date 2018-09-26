package com.ivansadovyi.sdk;

import io.reactivex.Completable;
import io.reactivex.Observable;

public abstract class OneFeedPlugin {

	public abstract Observable<FeedItem> loadNextItems();

	public Completable reset() {
		return Completable.complete();
	}

	public Observable<FeedItem> refresh() {
		return reset().andThen(loadNextItems());
	}
}
