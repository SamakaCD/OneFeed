package com.ivansadovyi.truba;

import android.support.annotation.NonNull;

import org.junit.Assert;
import org.junit.Test;

public class ExampleUnitTest {

	private interface Action {

	}

	private static class IncrementAction implements Action {

		private int value;

		public IncrementAction(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	private static class Store {

		private int value;

		public Store(int value) {
			this.value = value;
		}

		public void increment(int value) {
			this.value += value;
		}

		public int getValue() {
			return value;
		}
	}

	private static class CounterMiddleware implements Middleware<Action> {

		private Store store;

		public CounterMiddleware(Store store) {
			this.store = store;
		}

		@Override
		public void onAction(@NonNull Action action, @NonNull ActionDispatcher<Action> busDispatcher, @NonNull ActionDispatcher<Action> nextMiddlewareDispatcher) {
			if (action instanceof IncrementAction) {
				store.increment(((IncrementAction) action).getValue());
			}

			nextMiddlewareDispatcher.dispatch(action);
		}
	}

	@Test
	public void test() {
		Store store = new Store(5);
		Bus<Action> bus = new Bus<>();
		bus.addMiddleware(new CounterMiddleware(store));
		bus.dispatch(new IncrementAction(3));
		Assert.assertEquals(8, store.getValue());
	}
}