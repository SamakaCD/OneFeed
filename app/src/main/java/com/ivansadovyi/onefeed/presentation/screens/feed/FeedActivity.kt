package com.ivansadovyi.onefeed.presentation.screens.feed

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivansadovyi.onefeed.R
import javax.inject.Inject

class FeedActivity : Activity() {

	@Inject
	lateinit var viewModel: FeedViewModel

	private lateinit var layoutManager: LinearLayoutManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		//setContentView(R.layout.activity_feed)
//		AndroidInjection.inject(this)
//		val binding = DataBindingUtil.setContentView<ActivityFeedBinding>(this, R.layout.activity_feed)
//		binding.viewModel = viewModel
//		setupRecyclerView()
	}

//	private fun setupRecyclerView() {
//		layoutManager = LinearLayoutManager(this)
//		recyclerView.layoutManager = layoutManager
//		recyclerView.adapter = FeedRecyclerViewAdapter()
//		setupPagination()
//	}
//
//	private fun setupPagination() {
//		recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//			override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//				val visibleItemCount = layoutManager.childCount
//				val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
//				val totalItemCount = layoutManager.itemCount
//				if (lastVisibleItemPosition + visibleItemCount * 2 >= totalItemCount) {
//					viewModel.loadMore()
//				}
//			}
//		})
//	}
}
