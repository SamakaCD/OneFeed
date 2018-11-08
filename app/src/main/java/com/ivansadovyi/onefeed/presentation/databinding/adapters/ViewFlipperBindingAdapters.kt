package com.ivansadovyi.onefeed.presentation.databinding.adapters

import android.widget.ViewFlipper
import androidx.databinding.BindingAdapter

object ViewFlipperBindingAdapters {

	@JvmStatic
	@BindingAdapter("displayedChild")
	fun setDisplayedChild(viewFlipper: ViewFlipper, displayedChild: Int) {
		if (viewFlipper.displayedChild != displayedChild) {
			viewFlipper.displayedChild = displayedChild
		}
	}
}
