package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.oauth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.PluginAuthorizationFragment
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.AuthorizationParams
import com.ivansadovyi.sdk.auth.OAuthParams
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_authorization_oauth.*
import javax.inject.Inject

class OAuthFragment : PluginAuthorizationFragment(), OAuthView {

	@Inject
	lateinit var presenter: OAuthPresenter

	override fun onAttach(context: Context?) {
		AndroidSupportInjection.inject(this)
		super.onAttach(context)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return inflater.inflate(R.layout.fragment_authorization_oauth, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		webView.webViewClient = webViewClient
		progressDescription.text = getString(R.string.plugin_authorization_ouath_connecting, pluginDescriptor.name)
		presenter.onInit(pluginDescriptor, authParams)
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.onDestroy()
	}

	override fun finish() {
		activity?.finish()
	}

	override fun loadUrl(url: String) {
		webView.loadUrl(url)
	}

	override fun showWebView() {
		viewFlipper.displayedChild = WEB_VIEW_INDEX
	}

	private val webViewClient: WebViewClient = object : WebViewClient() {

		override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
			presenter.onRedirect(url)
			val authParams = authParams as OAuthParams
			return url.startsWith(authParams.callbackUrl)
		}

		override fun onPageFinished(view: WebView?, url: String?) {
			super.onPageFinished(view, url)
			presenter.onFinishLoading()
		}
	}

	companion object {
		private const val WEB_VIEW_INDEX = 1

		fun newInstance(pluginDescriptor: OneFeedPluginDescriptor, authParams: AuthorizationParams): OAuthFragment {
			return OAuthFragment().apply {
				arguments = PluginAuthorizationFragment.createArguments(pluginDescriptor, authParams)
			}
		}
	}
}
