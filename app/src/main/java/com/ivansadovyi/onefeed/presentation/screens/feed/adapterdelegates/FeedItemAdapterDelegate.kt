package com.ivansadovyi.onefeed.presentation.screens.feed.adapterdelegates

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.plugin.PluginIconCache
import com.ivansadovyi.onefeed.databinding.ItemFeedSimpleBinding
import com.ivansadovyi.onefeed.presentation.screens.feed.SubItemsAdapter
import com.ivansadovyi.onefeed.presentation.screens.feed.adapterdelegates.FeedItemAdapterDelegate.ViewHolder
import com.ivansadovyi.onefeed.presentation.screens.imageViewer.ImageViewerActivity
import com.ivansadovyi.onefeed.presentation.screens.userDetails.UserDetailsActivity
import com.ivansadovyi.sdk.SubItem

typealias OnSubItemClickListener = (SubItem, BundledFeedItem) -> Unit
typealias OnLikeClickListener = (BundledFeedItem) -> Unit
typealias OnItemClickListener = (BundledFeedItem) -> Unit

class FeedItemAdapterDelegate(
		private val pluginIconCache: PluginIconCache
) : AbsListItemAdapterDelegate<BundledFeedItem, Any, ViewHolder>() {

	private var onSubItemClickListener: OnSubItemClickListener? = null
	private var onLikeClickListener: OnLikeClickListener? = null
	private var onItemClickListener: OnItemClickListener? = null

	fun setOnSubItemClickListener(listener: OnSubItemClickListener) {
		onSubItemClickListener = listener
	}

	fun setOnLikeClickListener(listener: OnLikeClickListener) {
		onLikeClickListener = listener
	}

	fun setOnItemClickListener(listener: OnItemClickListener) {
		onItemClickListener = listener
	}

	override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
		return item is BundledFeedItem
	}

	override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val binding = ItemFeedSimpleBinding.inflate(layoutInflater, parent, false)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(item: BundledFeedItem, holder: ViewHolder, payloads: MutableList<Any>) {
		holder.binding.item = item
		holder.binding.isDateVisible = item.isDateVisible && item.publicationDate != null
		holder.binding.pluginIcon = pluginIconCache.get(item.pluginClassName)
		holder.binding.like.tag = item
		holder.binding.like.setOnClickListener(onLikeViewClickListener)
		holder.binding.itemContent.tag = item
		holder.binding.itemContent.setOnClickListener(onItemViewClickListener)

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

		if (item.subItems != null && item.subItems.isNotEmpty()) {
			val subItemsRecyclerView = holder.binding.subItemsRecyclerView
			val hasSubItemsAdapter = subItemsRecyclerView.adapter != null
			if (!hasSubItemsAdapter) {
				subItemsRecyclerView.adapter = SubItemsAdapter()
			}
			val subItemsAdapter = holder.binding.subItemsRecyclerView.adapter as SubItemsAdapter
			subItemsAdapter.setSubItems(item.subItems)
			subItemsAdapter.setOnSubItemCLickListener { subItem ->
				onSubItemClickListener?.invoke(subItem, item)
			}
		}

		holder.binding.feedItemAvatar.setOnClickListener {
			val intent = Intent(holder.itemView.context, UserDetailsActivity::class.java)
			intent.putExtras(UserDetailsActivity.createExtras(item.title, item.title))
			holder.itemView.context.startActivity(intent)
		}

		holder.binding.image.setOnClickListener {
			val intent = Intent(holder.itemView.context, ImageViewerActivity::class.java)
			intent.putExtra("url", item.images.first().url)
			holder.itemView.context.startActivity(intent)
		}
	}

	override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
		super.onViewRecycled(holder)
		if (holder is ViewHolder) {
			val context = holder.itemView.context
			Glide.with(context).clear(holder.binding.image)
		}
	}

	private val onLikeViewClickListener = View.OnClickListener { view ->
		val item = view.tag as BundledFeedItem
		onLikeClickListener?.invoke(item)
	}

	private val onItemViewClickListener = View.OnClickListener { view ->
		val item = view.tag as BundledFeedItem
		onItemClickListener?.invoke(item)
	}

	class ViewHolder(val binding: ItemFeedSimpleBinding) : RecyclerView.ViewHolder(binding.root)
}