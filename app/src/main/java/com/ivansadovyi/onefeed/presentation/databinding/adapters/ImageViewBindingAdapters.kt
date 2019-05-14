package com.ivansadovyi.onefeed.presentation.databinding.adapters

import android.graphics.PorterDuff
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object ImageViewBindingAdapters {

	@JvmStatic
	@BindingAdapter("srcUrl")
	fun setSrcUrl(imageView: ImageView, srcUrl: String?) {
		Glide.with(imageView)
				.load(srcUrl)
				.apply(RequestOptions.circleCropTransform())
				.into(imageView)
	}

	@JvmStatic
	@BindingAdapter("tintColor")
	fun setTintColor(imageView: ImageView, color: Int) {
		imageView.setColorFilter(color, PorterDuff.Mode.SRC_IN)
	}
}
