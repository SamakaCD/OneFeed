package com.ivansadovyi.onefeed.presentation.screens.initialSetup

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class InitialSetupViewModel : BaseObservable() {

	var facebookChoosed = false
	var twitterChoosed = false
	var instagramChoosed = false
	var anotherPluginChoosed = false

	fun chooseFacebook() {
		facebookChoosed = true
		notifyChange()
	}

	fun chooseTwitter() {
		twitterChoosed = true
		notifyChange()
	}

	fun chooseInstagram() {
		instagramChoosed = true
		notifyChange()
	}

	fun chooseAnotherPlugin() {
		anotherPluginChoosed = true
		notifyChange()
	}

	@Bindable
	fun isContinueEnabled(): Boolean {
		return facebookChoosed || twitterChoosed || instagramChoosed || anotherPluginChoosed;
	}
}