package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationCallbacks
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationStore
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import dagger.android.AndroidInjection
import javax.inject.Inject

class PluginAuthorizationActivity : AppCompatActivity(), PluginAuthorizationCallbacks {

	@Inject
	lateinit var pluginAuthorizationStore: PluginAuthorizationStore

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		AndroidInjection.inject(this)
		val pluginDescriptor = intent.getSerializableExtra(PLUGIN_DESCRIPTOR) as OneFeedPluginDescriptor
		pluginAuthorizationStore.startAuthorization(pluginDescriptor, this).subscribe()
	}

	override fun onLaunchOAuthUrl(url: String) {
		val builder = CustomTabsIntent.Builder()
		val customTabsIntent = builder.build()
		customTabsIntent.launchUrl(this, Uri.parse(url))
	}

	override fun onUnsupportedAuthorizationMethod() {
		Toast.makeText(this, "This authorization method is not supported", Toast.LENGTH_LONG).show()
	}

	companion object {

		private const val PLUGIN_DESCRIPTOR = "plugin_descriptor"

		fun createExtras(pluginDescriptor: OneFeedPluginDescriptor): Bundle {
			return Bundle().apply {
				putSerializable(PLUGIN_DESCRIPTOR, pluginDescriptor)
			}
		}
	}
}
