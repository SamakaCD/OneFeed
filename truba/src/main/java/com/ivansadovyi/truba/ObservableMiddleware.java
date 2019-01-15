package com.ivansadovyi.truba;

import java.util.HashSet;
import java.util.Set;

import androidx.annotation.NonNull;

public class ObservableMiddleware<Action> implements Middleware<Action> {

	private Set<ObservableMiddlewareSubscriber<Action>> subscribers = new HashSet<>();

	public Disposer subscribe(final ObservableMiddlewareSubscriber<Action> subscriber) {
		subscribers.add(subscriber);
		return new Disposer() {
			@Override
			public void dispose() {
				subscribers.remove(subscriber);
			}
		};
	}

	@Override
	public void onAction(@NonNull Action action, @NonNull ActionDispatcher<Action> busDispatcher, @NonNull ActionDispatcher<Action> nextMiddlewareDispatcher) {
		notifySubscribers(action);
		nextMiddlewareDispatcher.dispatch(action);
	}

	private void notifySubscribers(Action action) {
		for (ObservableMiddlewareSubscriber<Action> subscriber : subscribers) {
			subscriber.onAction(action);
		}
	}
}
