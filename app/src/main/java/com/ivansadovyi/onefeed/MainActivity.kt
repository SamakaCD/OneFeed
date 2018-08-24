package com.ivansadovyi.onefeed

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.ivansadovyi.sdk.OneFeedPlugin
import dalvik.system.DexClassLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private val recyclerViewAdapter = FeedRecyclerViewAdapter()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setupRecyclerView()

		val plugin = createExternalPluginInstance()
		plugin.loadNextItems(null).subscribe {
			recyclerViewAdapter.addItem(it)
		}
	}

	private fun setupRecyclerView() {
		recyclerView.adapter = recyclerViewAdapter
		recyclerView.layoutManager = LinearLayoutManager(this)
	}

	private fun createExternalPluginInstance(): OneFeedPlugin {
		val packageInfo = packageManager.getPackageInfo(PLUGIN_PACKAGE, PackageManager.GET_META_DATA)
		val dexClassLoader = DexClassLoader(packageInfo.applicationInfo.sourceDir,
				filesDir.absolutePath, null, classLoader)
		val pluginClass = dexClassLoader.loadClass(PLUGIN_CLASS_NAME)
		return pluginClass.newInstance() as OneFeedPlugin
	}

	companion object {
		private const val PLUGIN_PACKAGE = "com.ivansadovyi.testplugin"
		private const val PLUGIN_CLASS_NAME = "com.ivansadovyi.testplugin.TestPlugin"
	}
}
