package com.ivansadovyi.truba.saga

interface SagaAction {

	class Dispatch<T>(val action: T)
}
