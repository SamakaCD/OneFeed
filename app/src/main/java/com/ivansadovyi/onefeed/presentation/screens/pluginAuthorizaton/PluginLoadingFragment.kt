package com.ivansadovyi.onefeed.presentation.screens.pluginAuthorizaton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ivansadovyi.onefeed.R

class PluginLoadingFragment : Fragment() {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_authorization_loading_plugin, container, false)
	}
}
