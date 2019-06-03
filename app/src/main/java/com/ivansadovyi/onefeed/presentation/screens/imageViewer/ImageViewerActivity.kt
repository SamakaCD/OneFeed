package com.ivansadovyi.onefeed.presentation.screens.imageViewer

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.activity_image_viewer.*

class ImageViewerActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
		setContentView(com.ivansadovyi.onefeed.R.layout.activity_image_viewer)
		val url = intent.getStringExtra("url")
		Glide.with(imageView)
				.load(url)
				.into(object : SimpleTarget<Drawable>() {
					override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
						imageView.setImageDrawable(resource)
					}
				})

		supportActionBar?.hide()
	}

	fun onBackClick(v: View) {
		finish()
	}
}
