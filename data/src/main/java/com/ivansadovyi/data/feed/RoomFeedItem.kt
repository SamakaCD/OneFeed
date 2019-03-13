package com.ivansadovyi.data.feed

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ivansadovyi.sdk.FeedItem
import java.util.*

@Entity(tableName = "feedItems")
class RoomFeedItem(
		@PrimaryKey
		var id: String,
		var title: String,
		var content: String?,
		var publicationDate: Date?,
		var avatarImageUrl: String?,
		var pluginClassName: String
)