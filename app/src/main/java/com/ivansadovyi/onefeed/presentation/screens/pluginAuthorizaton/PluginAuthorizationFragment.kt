package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.AuthorizationParams

open class PluginAuthorizationFragment : Fragment() {

	val pluginDescriptor: OneFeedPluginDescriptor
		get() = arguments!!.getSerializable(PLUGIN_DESCRIPTOR) as OneFeedPluginDescriptor

	val authParams: AuthorizationParams
		get() = arguments!!.getSerializable(AUTHORIZATION_PARAMS) as AuthorizationParams

	companion object {

		private const val PLUGIN_DESCRIPTOR = "plugin_descriptor"
		private const val AUTHORIZATION_PARAMS = "authorization_params"

		fun createArguments(pluginDescriptor: OneFeedPluginDescriptor, authParams: AuthorizationParams): Bundle {
			return Bundle().apply {
				putSerializable(PLUGIN_DESCRIPTOR, pluginDescriptor)
				putSerializable(AUTHORIZATION_PARAMS, authParams)
			}
		}
	}
}
