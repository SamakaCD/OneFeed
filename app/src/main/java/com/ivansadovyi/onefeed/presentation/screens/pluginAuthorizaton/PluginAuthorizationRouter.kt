package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ivansadovyi.domain.plugin.auth.empty.EmptyAuthorizaionParams
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.empty.EmptyAuthorizationFragment
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.oauth.OAuthFragment
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.AuthorizationParams
import com.ivansadovyi.sdk.auth.OAuthParams
import javax.inject.Inject

class PluginAuthorizationRouter @Inject constructor(private val activity: PluginAuthorizationActivity) {

	fun navigateToPluginLoading() {
		activity.supportFragmentManager.beginTransaction()
				.replace(android.R.id.content, PluginLoadingFragment())
				.commit()
	}

	fun navigateToAuthorization(pluginDescriptor: OneFeedPluginDescriptor, authParams: AuthorizationParams) {
		val fragment = createFragmentByAuthParams(pluginDescriptor, authParams)
		activity.supportFragmentManager.beginTransaction()
				.replace(android.R.id.content, fragment)
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit()
	}

	private fun createFragmentByAuthParams(pluginDescriptor: OneFeedPluginDescriptor, authParams: AuthorizationParams): Fragment {
		return when (authParams) {
			is OAuthParams -> OAuthFragment.newInstance(pluginDescriptor, authParams)
			is EmptyAuthorizaionParams -> EmptyAuthorizationFragment.newInstance(pluginDescriptor, authParams)
			else -> throw IllegalArgumentException("Can not create fragment for auth params ($authParams)")
		}
	}
}
