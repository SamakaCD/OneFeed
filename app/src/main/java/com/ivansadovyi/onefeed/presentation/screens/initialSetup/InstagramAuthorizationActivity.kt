package com.ivansadovyi.onefeed.presentation.screens.initialSetup

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ivansadovyi.onefeed.R

class InstagramAuthorizationActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_auth_instagram)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
	}

	fun onSignInClick(v: View) {
		setResult(Activity.RESULT_OK)
		finish()
	}
}