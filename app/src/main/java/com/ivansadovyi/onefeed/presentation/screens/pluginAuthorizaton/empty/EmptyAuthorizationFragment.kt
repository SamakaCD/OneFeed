package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.empty

import android.os.Bundle
import com.ivansadovyi.domain.plugin.PluginInteractor
import com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton.PluginAuthorizationFragment
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.auth.AuthorizationParams
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmptyAuthorizationFragment : PluginAuthorizationFragment() {

	@Inject
	lateinit var pluginInteractor: PluginInteractor

	private val job = SupervisorJob()
	private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		AndroidSupportInjection.inject(this)
		coroutineScope.launch {
			pluginInteractor.processAuthorizationResponse(pluginDescriptor, response = "")
			activity?.finish()
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		job.cancel()
	}

	companion object {

		fun newInstance(pluginDescriptor: OneFeedPluginDescriptor, authParams: AuthorizationParams): EmptyAuthorizationFragment {
			return EmptyAuthorizationFragment().apply {
				arguments = PluginAuthorizationFragment.createArguments(pluginDescriptor, authParams)
			}
		}
	}
}
