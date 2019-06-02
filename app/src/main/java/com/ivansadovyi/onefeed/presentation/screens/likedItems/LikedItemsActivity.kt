package com.ivansadovyi.onefeed.presentation.screens.likedItems

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.onefeed.databinding.ActivityLikedItemsBinding
import com.ivansadovyi.onefeed.presentation.screens.feed.FeedRecyclerViewAdapter
import com.ivansadovyi.onefeed.presentation.screens.feedItemDetails.FeedItemDetailsActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_feed.*
import javax.inject.Inject

class LikedItemsActivity : AppCompatActivity() {

	@Inject
	lateinit var viewModel: LikedItemsViewModel

	@Inject
	lateinit var adapter: FeedRecyclerViewAdapter

	private lateinit var layoutManager: LinearLayoutManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		AndroidInjection.inject(this)
		val binding = DataBindingUtil.setContentView<ActivityLikedItemsBinding>(this, R.layout.activity_liked_items)
		binding.viewModel = viewModel
		setupRecyclerView()
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
	}

	fun navigateToFeedItemDetails(itemId: String) {
		val intent = Intent(this, FeedItemDetailsActivity::class.java)
		intent.putExtras(FeedItemDetailsActivity.createExtras(itemId))
		startActivity(intent)
	}

	override fun onSupportNavigateUp(): Boolean {
		finish()
		return super.onSupportNavigateUp()
	}

	private fun setupRecyclerView() {
		layoutManager = LinearLayoutManager(this)
		recyclerView.layoutManager = layoutManager
		recyclerView.adapter = adapter
		recyclerView.itemAnimator = null
		adapter.setOnItemClickListener {
			navigateToFeedItemDetails(it.id)
		}
	}
}
