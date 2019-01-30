package com.ivansadovyi.onefeed.presentation.screens.feed

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.onefeed.databinding.ActivityFeedBinding
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.PluginAuthorizationActivity
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_feed.*
import javax.inject.Inject

class FeedActivity : AppCompatActivity(), FeedRouter {

	@Inject
	lateinit var viewModel: FeedViewModel

	private lateinit var layoutManager: LinearLayoutManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		AndroidInjection.inject(this)
		val binding = DataBindingUtil.setContentView<ActivityFeedBinding>(this, R.layout.activity_feed)
		binding.viewModel = viewModel
		setupRecyclerView()
	}

	override fun navigateToPluginAuthorization(pluginDescriptor: OneFeedPluginDescriptor) {
		val intent = Intent(this, PluginAuthorizationActivity::class.java)
		intent.putExtras(PluginAuthorizationActivity.createExtras(pluginDescriptor))
		startActivity(intent)
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.feed, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return when (item.itemId) {
			R.id.authorizeTwitter -> {
				viewModel.authorizeTwitter()
				return true
			}
			R.id.resetAuthorizations -> {
				viewModel.resetAuthorizations()
				return true
			}
			else -> super.onOptionsItemSelected(item)
		}
	}

	private fun setupRecyclerView() {
		layoutManager = LinearLayoutManager(this)
		recyclerView.layoutManager = layoutManager
		recyclerView.adapter = FeedRecyclerViewAdapter()
		setupPagination()
	}

	private fun setupPagination() {
		recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
			override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
				val visibleItemCount = layoutManager.childCount
				val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
				val totalItemCount = layoutManager.itemCount
				if (lastVisibleItemPosition + visibleItemCount * 2 >= totalItemCount) {
					viewModel.loadMore()
				}
			}
		})
	}
}
