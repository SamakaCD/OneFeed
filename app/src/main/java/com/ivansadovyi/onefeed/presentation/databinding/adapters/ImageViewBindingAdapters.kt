package com.ivansadovyi.onefeed.presentation.databinding.adapters

import android.graphics.PorterDuff
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object ImageViewBindingAdapters {

	@JvmStatic
	@BindingAdapter(value = ["srcUrl", "applyRounding"], requireAll = false)
	fun setSrcUrl(imageView: ImageView, srcUrl: String?, applyRounding: Boolean?) {
		Glide.with(imageView)
				.load(srcUrl)
				.apply {
					if (applyRounding == true) {
						apply(RequestOptions.circleCropTransform())
					}
				}
				.into(imageView)
	}

	@JvmStatic
	@BindingAdapter("tintColor")
	fun setTintColor(imageView: ImageView, color: Int) {
		imageView.setColorFilter(color, PorterDuff.Mode.SRC_IN)
	}
}
