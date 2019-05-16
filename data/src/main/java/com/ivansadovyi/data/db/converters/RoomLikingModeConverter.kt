package com.ivansadovyi.data.db.converters

import androidx.room.TypeConverter
import com.ivansadovyi.sdk.FeedItem

object RoomLikingModeConverter {

	@JvmStatic
	@TypeConverter
	fun fromLikingMode(likingMode: FeedItem.LikingMode): String {
		return likingMode.name
	}

	@JvmStatic
	@TypeConverter
	fun toLikingMode(likingMode: String): FeedItem.LikingMode {
		return FeedItem.LikingMode.valueOf(likingMode)
	}
}