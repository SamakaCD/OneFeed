package com.ivansadovyi.onefeed.presentation.screens.feed.adapterdelegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.sdk.FeedItem

class FeedItemAdapterDelegate : AdapterDelegate<List<Any>>() {

	override fun isForViewType(items: List<Any>, position: Int): Boolean {
		return items[position] is FeedItem
	}

	override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val itemView = layoutInflater.inflate(R.layout.item_feed_simple, parent, false)
		return ViewHolder(itemView)
	}

	override fun onBindViewHolder(items: List<Any>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
		val item = items[position] as FeedItem
		with (holder as ViewHolder) {
			title.text = item.title
			content.text = item.content
		}
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		val title = itemView.findViewById<TextView>(R.id.title)
		val content = itemView.findViewById<TextView>(R.id.content)
	}
}