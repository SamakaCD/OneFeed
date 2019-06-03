package com.ivansadovyi.onefeed.presentation.screens.userDetails

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivansadovyi.domain.feed.FeedItemsInteractor
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.domain.utils.Disposable
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.onefeed.presentation.databinding.adapters.RecyclerViewBindingAdapters
import com.ivansadovyi.onefeed.presentation.screens.feed.FeedRecyclerViewAdapter
import com.ivansadovyi.onefeed.presentation.screens.feedItemDetails.FeedItemDetailsActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_user_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDetailsActivity : AppCompatActivity() {

	@Inject
	lateinit var adapter: FeedRecyclerViewAdapter

	@Inject
	lateinit var feedItemsStore: FeedItemsStore

	@Inject
	lateinit var feedItemsInteractor: FeedItemsInteractor

	private var disposable: Disposable? = null
	private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		AndroidInjection.inject(this)
		setContentView(R.layout.activity_user_details)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		supportActionBar?.title = intent.getStringExtra(USER_NAME)

		recyclerView.layoutManager = LinearLayoutManager(this)
		recyclerView.adapter = adapter
		recyclerView.itemAnimator = null

		adapter.setOnItemClickListener {
			navigateToFeedItemDetails(it.id)
		}

		adapter.setOnLikeClickListener { item ->
			coroutineScope.launch {
				feedItemsInteractor.likeItem(item)
			}
		}

		RecyclerViewBindingAdapters.setItems(recyclerView, feedItemsStore.getItemsOfUser(intent.getStringExtra(USER_ID)))
		disposable = feedItemsStore.observe {
			RecyclerViewBindingAdapters.setItems(recyclerView, feedItemsStore.getItemsOfUser(intent.getStringExtra(USER_ID)))
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		disposable?.invoke()
	}

	override fun onSupportNavigateUp(): Boolean {
		finish()
		return super.onSupportNavigateUp()
	}

	fun navigateToFeedItemDetails(itemId: String) {
		val intent = Intent(this, FeedItemDetailsActivity::class.java)
		intent.putExtras(FeedItemDetailsActivity.createExtras(itemId))
		startActivity(intent)
	}

	companion object {

		private const val USER_NAME = "user_name"
		private const val USER_ID = "user_id"

		fun createExtras(userName: String, userId: String): Bundle {
			return Bundle().apply {
				putString(USER_NAME, userName)
				putString(USER_ID, userId)
			}
		}
	}
}
