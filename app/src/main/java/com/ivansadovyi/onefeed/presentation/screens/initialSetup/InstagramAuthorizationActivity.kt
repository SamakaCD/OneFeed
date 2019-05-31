package com.ivansadovyi.onefeed.presentation.screens.initialSetup

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ivansadovyi.onefeed.R

class InstagramAuthorizationActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_auth)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		supportActionBar?.title = getString(R.string.plugin_authorization_screen_title_with_name,
				intent.getStringExtra("name"))
	}

	fun onSignInClick(v: View) {
		val d = ProgressDialog(this)
		d.setMessage(getString(R.string.please_wait))
		d.show()

		window.decorView.postDelayed({
			setResult(Activity.RESULT_OK)
			finish()
		}, (Math.random() * 3000).toLong() + 1000)
	}
}