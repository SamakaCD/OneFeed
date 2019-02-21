package com.ivansadovyi.onefeed.presentation.databinding.adapters

import android.text.format.DateUtils
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import java.util.*

object TextViewBindingAdapters {

	@JvmStatic
	@BindingAdapter("date")
	fun setRelativeDate(textView: TextView, date: Date?) {
		if (date != null) {
			val diff = Date().time - date.time
			textView.text = if (diff < DateUtils.HOUR_IN_MILLIS) {
				DateUtils.getRelativeTimeSpanString(date.time, Date().time, DateUtils.SECOND_IN_MILLIS)
			} else {
				DateUtils.getRelativeDateTimeString(textView.context, date.time, DateUtils.SECOND_IN_MILLIS,
						2 * DateUtils.DAY_IN_MILLIS, 0)
			}
		} else {
			textView.text = ""
		}
	}

	@JvmStatic
	@BindingAdapter("asyncText")
	fun setAsyncText(textView: AppCompatTextView, text: CharSequence) {
		val params = TextViewCompat.getTextMetricsParams(textView)
		textView.setTextFuture(PrecomputedTextCompat.getTextFuture(text, params, null))
	}
}
