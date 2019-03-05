package com.ivansadovyi.builtinplugins.recommendations

import android.graphics.Bitmap
import android.graphics.Color
import com.ivansadovyi.builtinplugins.R
import com.ivansadovyi.sdk.FeedItem
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.SubItem
import java.util.Collections.emptyList

class RecommendationsPlugin : OneFeedPlugin() {

	private var didShowRecommendations = false

	override fun getIcon(): Bitmap {
		return Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565)
	}

	override fun loadNextItems(): Iterable<FeedItem> {
		if (didShowRecommendations) {
			return emptyList()
		}

		val item = FeedItem.Builder()
				.setId(ITEM_ID)
				.setPriority(FeedItem.Priority.HIGH)
				.setTitle(context.getString(R.string.recommendations_item_title))
				.setSubItems(listOf(
						SubItem.Builder()
								.setId("twitter")
								.setTitle("Twitter")
								.setIconColor(Color.parseColor("#ff03a9f4"))
								.build(),
						SubItem.Builder()
								.setId("facebook")
								.setTitle("Facebook")
								.setIconColor(Color.parseColor("#ff03a9f4"))
								.build()
				))
				.build()

		return listOf(item)
	}

	companion object {

		private const val ITEM_ID = "1afec80b-2315-49db-97c7-cbe12f31921b"

		val DESCRIPTOR = OneFeedPluginDescriptor.Builder()
				.setName("Plugin recommendations")
				.setClassName(RecommendationsPlugin::class.java.name)
				.build()
	}
}
