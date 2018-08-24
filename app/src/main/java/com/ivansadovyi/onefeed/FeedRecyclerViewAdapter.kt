package com.ivansadovyi.onefeed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ivansadovyi.sdk.FeedItem

class FeedRecyclerViewAdapter : RecyclerView.Adapter<FeedRecyclerViewAdapter.ViewHolder>() {

	private val items = mutableListOf<FeedItem>()

	fun addItem(item: FeedItem) {
		items.add(item)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val itemView = layoutInflater.inflate(R.layout.item_feed_simple, parent, false)
		return ViewHolder(itemView)
	}

	override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
		val item = items[position]
		viewHolder.title.text = item.title
		viewHolder.content.text = item.content
	}

	override fun getItemCount(): Int {
		return items.size
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		val title = itemView.findViewById<TextView>(R.id.title)
		val content = itemView.findViewById<TextView>(R.id.content)
	}
}
