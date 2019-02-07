package com.ivansadovyi.onefeed.presentation.screens.feed.adapterdelegates

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.plugin.PluginIconCache
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.onefeed.databinding.ItemFeedSimpleBinding
import com.ivansadovyi.onefeed.presentation.screens.feed.adapterdelegates.FeedItemAdapterDelegate.ViewHolder

class FeedItemAdapterDelegate(
		context: Context,
		private val pluginIconCache: PluginIconCache
) : AbsListItemAdapterDelegate<BundledFeedItem, Any, ViewHolder>() {

	private val pluginIconColor = ContextCompat.getColor(context, R.color.colorFeedPluginIcon)

	override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
		return item is BundledFeedItem
	}

	override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val binding = ItemFeedSimpleBinding.inflate(layoutInflater, parent, false)
		return ViewHolder(binding).apply {
			binding.pluginIcon.setColorFilter(pluginIconColor, PorterDuff.Mode.SRC_IN)
		}
	}

	override fun onBindViewHolder(item: BundledFeedItem, holder: ViewHolder, payloads: MutableList<Any>) {
		holder.binding.item = item
		holder.binding.pluginIcon.setImageBitmap(pluginIconCache.get(item.pluginClassName))
		if (item.images.isNotEmpty()) {
			val image = item.images.first()
			var requestOptions = RequestOptions()
			if (image.width != null && image.height != null) {
				requestOptions = requestOptions.override(image.width!!, image.height!!)
			}

			Glide.with(holder.binding.image)
					.load(image.url)
					.apply(requestOptions)
					.into(holder.binding.image)
		}
	}

	override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
		super.onViewRecycled(holder)
		if (holder is ViewHolder) {
			val context = holder.itemView.context
			Glide.with(context).clear(holder.binding.image)
		}
	}

	class ViewHolder(val binding: ItemFeedSimpleBinding) : RecyclerView.ViewHolder(binding.root)
}