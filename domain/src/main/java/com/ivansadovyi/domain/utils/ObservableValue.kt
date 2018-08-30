package com.ivansadovyi.domain.utils

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ObservableValue<T>(defaultValue: T) : ReadWriteProperty<ObservableStore<*>, T> {

	private var value = defaultValue

	override fun getValue(thisRef: ObservableStore<*>, property: KProperty<*>): T {
		return value
	}

	override fun setValue(thisRef: ObservableStore<*>, property: KProperty<*>, value: T) {
		this.value = value
		thisRef.notifyChange()
	}
}
