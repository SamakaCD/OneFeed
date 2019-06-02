package com.ivansadovyi.onefeed.presentation.screens.accounts

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ivansadovyi.domain.feed.FeedItemsStore
import com.ivansadovyi.onefeed.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_accounts.*
import javax.inject.Inject

class AccountsActivity : AppCompatActivity() {

	@Inject
	lateinit var feedItemsStore: FeedItemsStore

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		AndroidInjection.inject(this)
		setContentView(R.layout.activity_accounts)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
	}

	fun onRemoveFacebookClick(v: View) {
		AlertDialog.Builder(this)
				.setMessage(R.string.account_log_out_confirm)
				.setPositiveButton(android.R.string.yes) { _, _ ->
					accountFacebook.visibility = View.GONE
					feedItemsStore.setSocialFilter("com.ivansadovyi.data.plugin.just.facebook.FacebookPlugin")
				}
				.setNegativeButton(android.R.string.no, null)
				.show()
	}

	override fun onSupportNavigateUp(): Boolean {
		finish()
		return super.onSupportNavigateUp()
	}
}