package com.ivansadovyi.truba;

public interface ActionDispatcher<Action> {

	void dispatch(Action action);

}
