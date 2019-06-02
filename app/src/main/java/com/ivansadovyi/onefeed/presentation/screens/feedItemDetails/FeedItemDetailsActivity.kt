package com.ivansadovyi.onefeed.presentation.screens.feedItemDetails

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.onefeed.databinding.ActivityFeedItemDetailsBinding
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_feed_item_details.*
import javax.inject.Inject

class FeedItemDetailsActivity : AppCompatActivity() {

	@Inject
	lateinit var viewModel: FeedItemDetailsViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		AndroidInjection.inject(this)

		val binding = DataBindingUtil.setContentView<ActivityFeedItemDetailsBinding>(this, R.layout.activity_feed_item_details)
		binding.viewModel = viewModel
		supportActionBar?.setDisplayShowTitleEnabled(false)

		val feedItemId = intent.getStringExtra(FEED_ITEM_ID)
		viewModel.onInit(feedItemId)
	}

	override fun onResume() {
		super.onResume()
		viewModel.onResume()
	}

	override fun onPause() {
		super.onPause()
		viewModel.onPause()
	}

	fun onAddCommentClick(v: View) {
		val d = ProgressDialog(this)
		d.setMessage(getString(R.string.please_wait))
		d.show()

		window.decorView.postDelayed({
			newCommentInput.clearFocus()
			viewModel.onSendNewComment()
		}, (Math.random() * 3000).toLong() + 1000)

	}

	companion object {

		private const val FEED_ITEM_ID = "feed_item_id"

		fun createExtras(feedItemId: String): Bundle {
			return Bundle().apply {
				putString(FEED_ITEM_ID, feedItemId)
			}
		}
	}
}
