package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class PluginAuthorizationActivity : AppCompatActivity(), PluginAuthorizationView, HasSupportFragmentInjector {

	@Inject
	lateinit var presenter: PluginAuthorizationPresenter

	@Inject
	lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

	override fun onCreate(savedInstanceState: Bundle?) {
		AndroidInjection.inject(this)
		super.onCreate(savedInstanceState)
		supportActionBar?.elevation = 0f
		val pluginDescriptor = intent.getSerializableExtra(PLUGIN_DESCRIPTOR) as OneFeedPluginDescriptor
		presenter.onInit(pluginDescriptor)
		setupToolbar(pluginDescriptor)
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.onDestroy()
	}

	override fun showUnsupportedAuthorizationMethodError() {
		Toast.makeText(this, R.string.plugin_authorization_not_supported, Toast.LENGTH_LONG).show()
		finish()
	}

	override fun supportFragmentInjector(): AndroidInjector<Fragment> {
		return fragmentInjector
	}

	private fun setupToolbar(pluginDescriptor: OneFeedPluginDescriptor) {
		supportActionBar?.title = getString(R.string.plugin_authorization_screen_title_with_name, pluginDescriptor.name.orEmpty())
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
