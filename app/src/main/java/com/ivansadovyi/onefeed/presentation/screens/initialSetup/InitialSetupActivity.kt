package com.ivansadovyi.onefeed.presentation.screens.initialSetup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.onefeed.databinding.ActivityInitialSetupBinding

class InitialSetupActivity : AppCompatActivity() {

	val viewModel = InitialSetupViewModel()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding = DataBindingUtil.setContentView<ActivityInitialSetupBinding>(this, R.layout.activity_initial_setup)
		binding.viewModel = viewModel
	}

	fun onChooseInstagram(v: View) {
		startActivityForResult(Intent(this, InstagramAuthorizationActivity::class.java), 1)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)

		if (resultCode == Activity.RESULT_OK) {
			when (requestCode) {
				1 -> viewModel.chooseInstagram()
			}
		}
	}
}