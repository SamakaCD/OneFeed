package com.ivansadovyi.onefeed.presentation.screens.feed

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.ivansadovyi.domain.plugin.PluginIconCache
import com.ivansadovyi.onefeed.presentation.screens.feed.adapterdelegates.FeedItemAdapterDelegate
import com.ivansadovyi.onefeed.presentation.screens.feed.adapterdelegates.OnLikeClickListener
import com.ivansadovyi.onefeed.presentation.screens.feed.adapterdelegates.OnSubItemClickListener
import com.ivansadovyi.onefeed.presentation.screens.feed.adapterdelegates.PaginationLoadingAdaterDelegate
import com.ivansadovyi.onefeed.presentation.utils.recyclerview.BindableRecyclerViewAdapter
import com.ivansadovyi.sdk.FeedItem
import javax.inject.Inject

class FeedRecyclerViewAdapter @Inject constructor(
		pluginIconCache: PluginIconCache
) : AsyncListDifferDelegationAdapter<Any>(DIFF_CALLBACK), BindableRecyclerViewAdapter<Any> {

	private val feedItemAdapterDelegate = FeedItemAdapterDelegate(pluginIconCache)

	init {
		delegatesManager.addDelegate(feedItemAdapterDelegate)
				.addDelegate(PaginationLoadingAdaterDelegate())
	}

	fun setOnSubItemClickListener(listener: OnSubItemClickListener) {
		feedItemAdapterDelegate.setOnSubItemClickListener(listener)
	}

	fun setOnLikeClickListener(listener: OnLikeClickListener) {
		feedItemAdapterDelegate.setOnLikeClickListener(listener)
	}

	companion object {

		val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Any>() {
			override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
				if (oldItem is FeedItem && newItem is FeedItem) {
					return oldItem.id == newItem.id
				}

				return false
			}

			override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
				if (oldItem is FeedItem && newItem is FeedItem) {
					return oldItem.isLiked == newItem.isLiked
				}

				return true
			}
		}
	}
}
