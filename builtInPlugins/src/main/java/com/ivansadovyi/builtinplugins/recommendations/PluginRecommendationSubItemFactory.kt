package com.ivansadovyi.builtinplugins.recommendations

import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.content.ContextCompat
import com.ivansadovyi.builtinplugins.R
import com.ivansadovyi.onefeed.plugin.feedly.FeedlyPlugin
import com.ivansadovyi.onefeed.plugin.twitter.TwitterPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import com.ivansadovyi.sdk.SubItem

class PluginRecommendationSubItemFactory(private val context: Context) {

	fun create(descriptor: OneFeedPluginDescriptor): SubItem {
		return when (descriptor) {
			TwitterPlugin.DESCRIPTOR -> SubItem.Builder()
					.setId(RecommendationsPlugin.TWITTER_ID)
					.setTitle(context.getString(R.string.recommendations_sub_item_twitter))
					.setIconColor(ContextCompat.getColor(context, R.color.colorTwitter))
					.setIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_twitter))
					.build()

			FeedlyPlugin.DESCRIPTOR -> SubItem.Builder()
					.setId(RecommendationsPlugin.FEEDLY_ID)
					.setTitle(context.getString(R.string.recommendations_sub_item_feedly))
					.setIconColor(ContextCompat.getColor(context, R.color.colorFeedly))
					.setIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_feedly))
					.build()

			else -> throw IllegalArgumentException("Unsupported plugin: [$descriptor]")
		}
	}
}
