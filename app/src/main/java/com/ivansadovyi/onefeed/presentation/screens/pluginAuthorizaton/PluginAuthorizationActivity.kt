package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.onefeed.databinding.ActivityPluginAuthorizationBinding
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_plugin_authorization.*
import javax.inject.Inject

class PluginAuthorizationActivity : AppCompatActivity(), PluginAuthorizationView {

	@Inject
	lateinit var viewModelFactory: PluginAuthorizationViewModelFactory

	lateinit var viewModel: PluginAuthorizationViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		AndroidInjection.inject(this)
		val pluginDescriptor = intent.getSerializableExtra(PLUGIN_DESCRIPTOR) as OneFeedPluginDescriptor
		viewModel = viewModelFactory.create(pluginDescriptor)

		setupView()
		setupToolbar(pluginDescriptor)
	}

	override fun loadUrl(url: String) {
		webView?.loadUrl(url)
	}

	override fun showUnsupportedAuthorizationMethodError() {
		Toast.makeText(this, "This authorization method is not supported", Toast.LENGTH_LONG).show()
	}

	private fun setupToolbar(pluginDescriptor: OneFeedPluginDescriptor) {
		supportActionBar?.title = getString(R.string.plugin_authorization_screen_title_with_name, pluginDescriptor.name.orEmpty())
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
	}

	private fun setupView() {
		val binding = DataBindingUtil.setContentView<ActivityPluginAuthorizationBinding>(this, R.layout.activity_plugin_authorization)
		binding.viewModel = viewModel
		webView.webViewClient = webViewClient
	}

	override fun onSupportNavigateUp(): Boolean {
		finish()
		return true
	}

	private val webViewClient: WebViewClient = object : WebViewClient() {

		override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
			viewModel.onRedirect(url)
			return !url.startsWith(HTTP_SCHEME)
		}

		override fun onPageFinished(view: WebView?, url: String?) {
			super.onPageFinished(view, url)
			viewModel.onFinishLoading()
		}
	}

	companion object {

		private const val PLUGIN_DESCRIPTOR = "plugin_descriptor"
		private const val HTTP_SCHEME = "http"

		fun createExtras(pluginDescriptor: OneFeedPluginDescriptor): Bundle {
			return Bundle().apply {
				putSerializable(PLUGIN_DESCRIPTOR, pluginDescriptor)
			}
		}
	}
}
