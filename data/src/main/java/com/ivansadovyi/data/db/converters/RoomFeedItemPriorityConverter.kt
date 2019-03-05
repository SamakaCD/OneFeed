package com.ivansadovyi.data.db.converters

import androidx.room.TypeConverter
import com.ivansadovyi.sdk.FeedItem

object RoomFeedItemPriorityConverter {

	@JvmStatic
	@TypeConverter
	fun fromPriority(priority: FeedItem.Priority): String {
		return priority.name
	}

	@JvmStatic
	@TypeConverter
	fun toPriority(priority: String): FeedItem.Priority {
		return FeedItem.Priority.valueOf(priority)
	}
}
