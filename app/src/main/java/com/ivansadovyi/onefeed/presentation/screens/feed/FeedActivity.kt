package com.ivansadovyi.onefeed.presentation.screens.feed

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.onefeed.databinding.ActivityFeedBinding

class FeedActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding = DataBindingUtil.setContentView<ActivityFeedBinding>(this, R.layout.activity_feed)
		binding.viewModel = FeedViewModel()
		binding.recyclerView.adapter = FeedRecyclerViewAdapter()
		binding.recyclerView.layoutManager = LinearLayoutManager(this)
	}
}
