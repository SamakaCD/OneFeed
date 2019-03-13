package com.ivansadovyi.builtinplugins.recommendations

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.ivansadovyi.builtinplugins.R
import com.ivansadovyi.onefeed.plugin.twitter.TwitterPlugin
import com.ivansadovyi.sdk.FeedItem
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.SubItem
import java.util.*
import java.util.Collections.emptyList

class RecommendationsPlugin : OneFeedPlugin(), OneFeedPlugin.OnSubItemClickListener {

	private var didShowRecommendations = false
	private val pluginRecommendationSubItemFactory by lazy { PluginRecommendationSubItemFactory(context) }

	override fun getIcon(): Bitmap {
		return BitmapFactory.decodeResource(context.resources, R.drawable.ic_onefeed)
	}

	override fun loadNextItems(): Iterable<FeedItem> {
		if (didShowRecommendations) {
			return emptyList()
		}

		val authorizedPluginDescriptors = host.pluginManager.authorizedPluginDescriptors
		val subItems = PLUGINS_FOR_RECOMMENDATION.filter { !authorizedPluginDescriptors.contains(it) }
				.map(pluginRecommendationSubItemFactory::create)

		if (subItems.isEmpty()) {
			// Nothing to recommend
			return emptyList()
		}

		val item = FeedItem.Builder()
				.setId(ITEM_ID)
				.setTitle(context.getString(R.string.recommendations_item_title))
				.setPublicationDate(Date())
				.setDateVisible(false)
				.setSubItems(subItems)
				.build()

		return listOf(item)
	}

	override fun onSubItemClick(subItem: SubItem, feedItem: FeedItem) {
		when (subItem.id) {
			TWITTER_ID -> host.pluginManager.startAuthorization(TwitterPlugin.DESCRIPTOR)
		}
	}

	companion object {

		const val ITEM_ID = "com.ivansadovyi.builtinplugins.recommendations.RecommendationsItem"
		const val TWITTER_ID = "com.ivansadovyi.builtinplugins.recommendations.RecommendationsItem.Twitter"

		val DESCRIPTOR = OneFeedPluginDescriptor.Builder()
				.setName("Plugin recommendations")
				.setClassName(RecommendationsPlugin::class.java.name)
				.build()

		private val PLUGINS_FOR_RECOMMENDATION = listOf(TwitterPlugin.DESCRIPTOR)
	}
}
