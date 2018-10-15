package com.ivansadovyi.onefeed.presentation.bindingadapters

import androidx.databinding.BindingAdapter
import android.widget.ViewFlipper

object ViewFlipperBindingAdapters {

	@JvmStatic
	@BindingAdapter("displayedChild")
	fun setDisplayedChild(viewFlipper: ViewFlipper, displayedChild: Int) {
		viewFlipper.displayedChild = displayedChild
	}
}
