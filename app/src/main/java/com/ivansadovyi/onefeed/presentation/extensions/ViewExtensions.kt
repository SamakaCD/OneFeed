package com.ivansadovyi.onefeed.presentation.extensions

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat

fun View.setGestureListener(onTap: ((View) -> Unit)?, onDoubleTap: ((View) -> Unit)?) {
	val gestureDetector = GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {

		override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
			onTap?.invoke(this@setGestureListener)
			return super.onSingleTapConfirmed(e)
		}

		override fun onDoubleTap(e: MotionEvent?): Boolean {
			onDoubleTap?.invoke(this@setGestureListener)
			return super.onDoubleTap(e)
		}
	})

	setOnTouchListener { _, event ->
		gestureDetector.onTouchEvent(event)
		return@setOnTouchListener true
	}
}