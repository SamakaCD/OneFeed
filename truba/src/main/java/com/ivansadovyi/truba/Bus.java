package com.ivansadovyi.truba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bus<Action> implements ActionDispatcher<Action> {

	private List<Middleware<Action>> middleware = new ArrayList<>();

	public Bus<Action> addMiddleware(Middleware<Action>... middleware) {
		this.middleware.addAll(Arrays.asList(middleware));
		return this;
	}

	@Override
	public void dispatch(Action action) {
		if (middleware.isEmpty()) {
			// There is no middleware. Do nothing
			return;
		}

		ActionDispatcher<Action> firstMiddlewareDispatcher = createActionDispatcher(0);
		firstMiddlewareDispatcher.dispatch(action);
	}

	private ActionDispatcher<Action> createActionDispatcher(final int middlewareIndex) {
		return new ActionDispatcher<Action>() {
			@Override
			public void dispatch(Action action) {
				Middleware<Action> middleware = Bus.this.middleware.get(middlewareIndex);
				ActionDispatcher<Action> nextActionDispatcher = EMPTY_ACTION_DISPATCHER;

				if (middlewareIndex + 1 < Bus.this.middleware.size()) {
					nextActionDispatcher = createActionDispatcher(middlewareIndex + 1);
				}

				middleware.onAction(action, Bus.this, nextActionDispatcher);
			}
		};
	}

	private final ActionDispatcher<Action> EMPTY_ACTION_DISPATCHER = new ActionDispatcher<Action>() {
		@Override
		public void dispatch(Action action) {
			// NOP
		}
	};
}
