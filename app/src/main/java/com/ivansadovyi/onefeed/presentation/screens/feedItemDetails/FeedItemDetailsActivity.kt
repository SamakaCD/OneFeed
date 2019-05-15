package com.ivansadovyi.onefeed.presentation.screens.feedItemDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.onefeed.databinding.ActivityFeedItemDetailsBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class FeedItemDetailsActivity : AppCompatActivity() {

	@Inject
	lateinit var viewModel: FeedItemDetailsViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		AndroidInjection.inject(this)
		val binding = DataBindingUtil.setContentView<ActivityFeedItemDetailsBinding>(this, R.layout.activity_feed_item_details)
		binding.viewModel = viewModel

		val feedItemId = intent.getStringExtra(FEED_ITEM_ID)
		viewModel.onInit(feedItemId)
	}

	override fun onResume() {
		super.onResume()
		viewModel.onResume()
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
