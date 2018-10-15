package com.ivansadovyi.onefeed.presentation.screens.feed

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ivansadovyi.onefeed.R
import com.ivansadovyi.onefeed.presentation.utils.recyclerview.BindableRecyclerViewAdapter
import com.ivansadovyi.sdk.FeedItem

class FeedRecyclerViewAdapter : RecyclerView.Adapter<FeedRecyclerViewAdapter.ViewHolder>(), BindableRecyclerViewAdapter<FeedItem> {

	private var items = emptyList<FeedItem>()

	override fun setItems(items: List<FeedItem>) {
		this.items = items
		notifyDataSetChanged()
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
