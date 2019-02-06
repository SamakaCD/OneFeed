package com.ivansadovyi.data.plugin

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.collection.LruCache
import com.ivansadovyi.domain.plugin.PluginIconCache
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginIconCacheImpl @Inject constructor(application: Application) : PluginIconCache {

	private val runtimeCache = LruCache<String, Bitmap>(RUNTIME_CACHE_MAX_SIZE)
	private val cacheDir = File(application.cacheDir, DIR_NAME)

	init {
		cacheDir.mkdir()
	}

	override fun put(pluginClassName: String, icon: Bitmap) {
		runtimeCache.put(pluginClassName, icon)
		saveToDisk(pluginClassName, icon)
	}

	override fun get(pluginClassName: String): Bitmap {
		return runtimeCache[pluginClassName] ?: (readFromDisk(pluginClassName)
				?: throw IllegalStateException("Can not find an icon of plugin ($pluginClassName) in cache"))
	}

	private fun saveToDisk(pluginClassName: String, icon: Bitmap) {
		val file = File(cacheDir, pluginClassName)
		file.createNewFile()
		val stream = FileOutputStream(file)
		icon.compress(Bitmap.CompressFormat.PNG, 100, stream)
		stream.close()
	}

	private fun readFromDisk(pluginClassName: String): Bitmap? {
		val file = File(cacheDir, pluginClassName)
		if (!file.exists()) {
			return null
		}
		return BitmapFactory.decodeFile(file.path)
	}

	companion object {
		private const val RUNTIME_CACHE_MAX_SIZE = 32
		private const val DIR_NAME = "plugin_icons"
	}
}