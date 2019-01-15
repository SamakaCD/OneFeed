package com.ivansadovyi.truba.saga

fun <Action> actionBinding(onAction: suspend Action.(Action) -> Unit): suspend (Action) -> Unit {
	return {
		it.onAction(it)
	}
}
