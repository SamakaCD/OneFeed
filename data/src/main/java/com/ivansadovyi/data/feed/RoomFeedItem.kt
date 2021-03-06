package com.ivansadovyi.data.feed

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ivansadovyi.data.feed.RoomFeedItem.Companion.TABLE_NAME
import java.util.*

@Entity(tableName = TABLE_NAME)
class RoomFeedItem(
		@PrimaryKey
		var id: String,
		var title: String,
		var content: String?,
		var publicationDate: Date?,
		var isDateVisible: Boolean,
		var avatarImageUrl: String?,
		var pluginClassName: String,
		var hasDetails: Boolean,
		var isLikeable: Boolean,
		var isLiked: Boolean,
		var likesCount: Int
) {

	companion object {

		const val TABLE_NAME = "feedItems"
	}
}
