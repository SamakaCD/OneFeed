package com.ivansadovyi.onefeed.presentation.screens.feed.adapterdelegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.onefeed.presentation.screens.feed.PaginationLoadingItem

class PaginationLoadingAdaterDelegate : AdapterDelegate<List<Any>>() {

	override fun isForViewType(items: List<Any>, position: Int): Boolean {
		return items[position] is PaginationLoadingItem
	}

	override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val itemView = layoutInflater.inflate(R.layout.item_feed_padination_loading, parent, false)
		return ViewHolder(itemView)
	}

	override fun onBindViewHolder(items: List<Any>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {

	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
