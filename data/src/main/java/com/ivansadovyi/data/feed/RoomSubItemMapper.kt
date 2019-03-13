package com.ivansadovyi.data.feed

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.ivansadovyi.sdk.SubItem
import java.io.ByteArrayOutputStream

object RoomSubItemMapper {

	fun fromRoom(listItem: RoomSubItem): SubItem {
		val builder = SubItem.Builder()
				.setId(listItem.id)
				.setTitle(listItem.title)
				.setDescription(listItem.description)
				.setIconUrl(listItem.iconUrl)
				.setIconColor(listItem.iconColor)

		listItem.icon?.let { iconBytes ->
			val icon = BitmapFactory.decodeByteArray(iconBytes, 0, iconBytes.size)
			builder.setIcon(icon)
		}

		return builder.build()
	}

	fun toRoom(subItem: SubItem, feedItemId: String): RoomSubItem {
		val iconBytes = subItem.icon?.let { bitmap ->
			val stream = ByteArrayOutputStream()
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
			stream.toByteArray()
		}

		return with(subItem) {
			RoomSubItem(id, feedItemId, title, description, iconBytes, iconUrl, iconColor)
		}
	}
}
