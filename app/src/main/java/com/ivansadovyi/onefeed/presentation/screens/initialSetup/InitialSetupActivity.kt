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
		val intent = Intent(this, InstagramAuthorizationActivity::class.java)
		intent.putExtra("name", "Instagram")
		startActivityForResult(intent, 1)
	}

	fun onChooseFacebook(v: View) {
		val intent = Intent(this, InstagramAuthorizationActivity::class.java)
		intent.putExtra("name", "Facebook")
		startActivityForResult(intent, 2)
	}

	fun onChooseTwitter(v: View) {
		val intent = Intent(this, InstagramAuthorizationActivity::class.java)
		intent.putExtra("name", "Twitter")
		startActivityForResult(intent, 3)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)

		if (resultCode == Activity.RESULT_OK) {
			when (requestCode) {
				1 -> viewModel.chooseInstagram()
				2 -> viewModel.chooseFacebook()
				3 -> viewModel.chooseTwitter()
			}
		}
	}
}