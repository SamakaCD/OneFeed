package com.ivansadovyi.truba;

import androidx.annotation.NonNull;

public interface Middleware<Action> {

	void onAction(@NonNull Action action, @NonNull ActionDispatcher<Action> busDispatcher, @NonNull ActionDispatcher<Action> nextMiddlewareDispatcher);

}
