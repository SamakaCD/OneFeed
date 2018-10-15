package com.ivansadovyi.onefeed.presentation.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ivansadovyi.onefeed.presentation.utils.recyclerview.BindableRecyclerViewAdapter

object RecyclerViewBindingAdapters {

	@JvmStatic
	@BindingAdapter("items")
	@Suppress("UNCHECKED_CAST")
	fun <T> setItems(recyclerView: RecyclerView, items: List<T>) {
		if (recyclerView.adapter !is BindableRecyclerViewAdapter<*>) {
			throw IllegalArgumentException("Adapter of RecyclerView should implement BindableRecyclerViewAdapter interface")
		}

		(recyclerView.adapter as BindableRecyclerViewAdapter<T>).setItems(items)
	}
}
