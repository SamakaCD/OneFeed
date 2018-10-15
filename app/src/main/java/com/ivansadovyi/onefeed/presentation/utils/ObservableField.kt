package com.ivansadovyi.onefeed.presentation.utils

import androidx.databinding.BaseObservable
import kotlin.reflect.KProperty

class ObservableField<T>(private val fieldId: Int, private var value: T) {

	operator fun getValue(thisRef: BaseObservable, property: KProperty<*>): T {
		return value
	}

	operator fun setValue(thisRef: BaseObservable, property: KProperty<*>, value: T) {
		this.value = value
		thisRef.notifyPropertyChanged(fieldId)
	}
}