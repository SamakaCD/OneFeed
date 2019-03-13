package com.ivansadovyi.onefeed.presentation.screens.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivansadovyi.onefeed.databinding.ItemFeedSubItemBinding
import com.ivansadovyi.sdk.SubItem

typealias OnSubItemClickListener = (SubItem) -> Unit

class SubItemsAdapter : RecyclerView.Adapter<SubItemsAdapter.ViewHolder>() {

	private var subItems = emptyList<SubItem>()
	private var onSubItemClickListener: OnSubItemClickListener? = null

	override fun getItemCount(): Int {
		return subItems.size
	}

	fun setSubItems(items: List<SubItem>) {
		this.subItems = items
		notifyDataSetChanged()
	}

	fun setOnSubItemCLickListener(listener: OnSubItemClickListener) {
		onSubItemClickListener = listener
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val binding = ItemFeedSubItemBinding.inflate(layoutInflater, parent, false)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val subItem = subItems[position]
		holder.binding.subItem = subItem
		holder.itemView.setOnClickListener {
			onSubItemClickListener?.invoke(subItem)
		}

		val icon = subItem.icon
		if (icon != null) {
			holder.binding.icon.setImageBitmap(icon)
		} else {
			holder.binding.icon.setImageDrawable(null)
		}
	}

	class ViewHolder(val binding: ItemFeedSubItemBinding) : RecyclerView.ViewHolder(binding.root)
}
