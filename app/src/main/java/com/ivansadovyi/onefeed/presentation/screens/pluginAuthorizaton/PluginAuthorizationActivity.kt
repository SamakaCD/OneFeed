package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import dagger.android.AndroidInjection
import javax.inject.Inject

class PluginAuthorizationActivity : AppCompatActivity(), PluginAuthorizationView {

	@Inject
	lateinit var presenter: PluginAuthorizationPresenter

	private var webView: WebView? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		AndroidInjection.inject(this)
		setupView()

		val pluginDescriptor = intent.getSerializableExtra(PLUGIN_DESCRIPTOR) as OneFeedPluginDescriptor
		supportActionBar?.title = getString(R.string.plugin_authorization_screen_title_with_name, pluginDescriptor.name.orEmpty())
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		presenter.onInit(pluginDescriptor)
	}

	override fun finishAuthorization() {
		Toast.makeText(this, "YEAH", Toast.LENGTH_LONG).show()
		finish()
	}

	override fun loadUrl(url: String) {
		webView?.loadUrl(url)
	}

	override fun showUnsupportedAuthorizationMethodError() {
		Toast.makeText(this, "This authorization method is not supported", Toast.LENGTH_LONG).show()
	}

	private fun setupView() {
		webView = WebView(this)
		webView?.webViewClient = webViewClient
		setContentView(webView)

	}

	override fun onSupportNavigateUp(): Boolean {
		finish()
		return true
	}

	private val webViewClient: WebViewClient = object : WebViewClient() {

		override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
			return presenter.onLoadUrl(url)
		}
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
