package com.ivansadovyi.onefeed.presentation.databinding.adapters

import androidx.databinding.BindingAdapter
import android.view.View

object ViewBindingAdapters {

	@JvmStatic
	@BindingAdapter("visible")
	fun <T> setVisible(view: View, visible: Boolean) {
		view.visibility = if (visible) View.VISIBLE else View.GONE
	}
}
