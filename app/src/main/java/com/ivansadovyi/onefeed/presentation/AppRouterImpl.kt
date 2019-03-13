package com.ivansadovyi.onefeed.presentation

import android.app.Application
import android.content.Intent
import com.ivansadovyi.domain.app.AppRouter
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.PluginAuthorizationActivity
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRouterImpl @Inject constructor(private val application: Application) : AppRouter {

	override fun navigateToPluginAuthorization(pluginDescriptor: OneFeedPluginDescriptor) {
		val intent = Intent(application, PluginAuthorizationActivity::class.java)
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
		intent.putExtras(PluginAuthorizationActivity.createExtras(pluginDescriptor))
		application.startActivity(intent)
	}
}
