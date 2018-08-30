package com.ivansadovyi.onefeed.presentation.screens.feed

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.onefeed.databinding.ActivityFeedBinding
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_feed.*
import javax.inject.Inject

class FeedActivity : AppCompatActivity() {

	@Inject
	lateinit var viewModel: FeedViewModel

	@Inject
	lateinit var feedItemsStore: FeedItemsStore

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		AndroidInjection.inject(this)
		val binding = DataBindingUtil.setContentView<ActivityFeedBinding>(this, R.layout.activity_feed)
		binding.viewModel = viewModel
		setupRecyclerView()
	}

	private fun setupRecyclerView() {
		recyclerView.adapter = FeedRecyclerViewAdapter()
		recyclerView.layoutManager = LinearLayoutManager(this)
	}
}
