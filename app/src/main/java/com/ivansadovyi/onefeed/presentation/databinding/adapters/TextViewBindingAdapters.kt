package com.ivansadovyi.onefeed.presentation.databinding.adapters

import android.text.format.DateUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.util.*

object TextViewBindingAdapters {

	@JvmStatic
	@BindingAdapter("date")
	fun setRelativeDate(textView: TextView, date: Date) {
		textView.text = DateUtils.getRelativeDateTimeString(textView.context, date.time,
				DateUtils.SECOND_IN_MILLIS, DateUtils.HOUR_IN_MILLIS, 0)
	}
}
