package com.ivansadovyi.onefeed.presentation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.ivansadovyi.domain.plugin.RateLimitException
import com.ivansadovyi.onefeed.R
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

class GenericExceptionHandler @Inject constructor(private val context: Context) {

	val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
		when (throwable) {
			is RateLimitException -> showRateLimitError(throwable)
			else -> showUnexpectedError(throwable)
		}
	}

	private fun showRateLimitError(exception: RateLimitException) {
		val pluginName = exception.plugin.descriptor.name
		AlertDialog.Builder(context)
				.setMessage(context.getString(R.string.feed_rate_limit_error_message, pluginName))
				.setPositiveButton(R.string.got_it, null)
				.show()
	}

	private fun showUnexpectedError(throwable: Throwable) {
		throwable.printStackTrace()
		val stackTrace = Log.getStackTraceString(throwable)
		AlertDialog.Builder(context)
				.setTitle(R.string.unexpected_error_title)
				.setMessage(stackTrace)
				.setPositiveButton(R.string.got_it, null)
				.setNegativeButton(android.R.string.copy) { _, _ -> copyTextToClipboard(stackTrace) }
				.show()
	}

	private fun copyTextToClipboard(text: String) {
		val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
		val clip = ClipData.newPlainText(null, text)
		clipboard.primaryClip = clip
	}
}
