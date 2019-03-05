package com.ivansadovyi.builtinplugins.recommendations

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.content.ContextCompat
import com.ivansadovyi.builtinplugins.R
import com.ivansadovyi.onefeed.plugin.twitter.TwitterPlugin
import com.ivansadovyi.sdk.FeedItem
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.SubItem
import java.util.Collections.emptyList

class RecommendationsPlugin : OneFeedPlugin(), OneFeedPlugin.OnSubItemClickListener {

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
								.setId(TWITTER_ID)
								.setTitle(context.getString(R.string.recommendations_sub_item_twitter))
								.setIconColor(ContextCompat.getColor(context, R.color.colorTwitter))
								.setIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_twitter))
								.build()
				))
				.build()

		return listOf(item)
	}

	override fun onSubItemClick(subItem: SubItem, feedItem: FeedItem) {
		when (subItem.id) {
			TWITTER_ID -> host.pluginManager.startAuthorization(TwitterPlugin.DESCRIPTOR)
		}
	}

	companion object {

		private const val ITEM_ID = "1afec80b-2315-49db-97c7-cbe12f31921b"
		private const val TWITTER_ID = "42324058-fa4b-473b-8ce8-04e58c5cccc9"

		val DESCRIPTOR = OneFeedPluginDescriptor.Builder()
				.setName("Plugin recommendations")
				.setClassName(RecommendationsPlugin::class.java.name)
				.build()
	}
}
