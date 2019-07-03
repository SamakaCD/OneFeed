package com.ivansadovyi.onefeed.presentation.screens.initialSetup

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

class InstagramAuthorizationActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(com.ivansadovyi.onefeed.R.layout.activity_auth)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		supportActionBar?.title = getString(com.ivansadovyi.onefeed.R.string.plugin_authorization_screen_title_with_name,
				intent.getStringExtra("name"))
	}

	fun onSignInClick(v: View) {
		val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
		imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

		val d = ProgressDialog(this)
		d.setMessage(getString(com.ivansadovyi.onefeed.R.string.please_wait))
		d.show()

		window.decorView.postDelayed({
			setResult(Activity.RESULT_OK)
			finish()
		}, (Math.random() * 3000).toLong() + 1000)
	}
}