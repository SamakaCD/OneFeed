package com.ivansadovyi.truba;

public interface ObservableMiddlewareSubscriber<Action> {

	void onAction(Action action);

}
