package com.ivansadovyi.onefeed.presentation.screens.feed.adapterdelegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.ivansadovyi.onefeed.databinding.ItemFeedSimpleBinding
import com.ivansadovyi.sdk.FeedItem

class FeedItemAdapterDelegate : AdapterDelegate<List<Any>>() {

	override fun isForViewType(items: List<Any>, position: Int): Boolean {
		return items[position] is FeedItem
	}

	override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val binding = ItemFeedSimpleBinding.inflate(layoutInflater, parent, false)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(items: List<Any>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
		(holder as ViewHolder).binding.item = items[position] as FeedItem
	}

	class ViewHolder(val binding: ItemFeedSimpleBinding) : RecyclerView.ViewHolder(binding.root)
}