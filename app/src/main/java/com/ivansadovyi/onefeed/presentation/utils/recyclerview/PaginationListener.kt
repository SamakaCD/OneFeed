package com.ivansadovyi.onefeed.presentation.utils.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PaginationListener(
		private val layoutManager: LinearLayoutManager,
		private val throttling: Int = 0,
		private val loadNextPageCallback: () -> Unit
) : RecyclerView.OnScrollListener() {

	private var lastScrollEventTime = 0L

	override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
		super.onScrolled(recyclerView, dx, dy)
		val currentTime = System.currentTimeMillis()
		if (currentTime - lastScrollEventTime > throttling) {
			this.lastScrollEventTime = currentTime
			val visibleItemCount = layoutManager.childCount
			val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
			val totalItemCount = layoutManager.itemCount
			if (lastVisibleItemPosition + visibleItemCount * 2 >= totalItemCount) {
				loadNextPageCallback()
			}
		}
	}
}
