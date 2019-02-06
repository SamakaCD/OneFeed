package com.ivansadovyi.domain.plugin

import android.graphics.Bitmap

interface PluginIconCache {

	fun put(pluginClassName: String, icon: Bitmap)

	fun get(pluginClassName: String): Bitmap
}
