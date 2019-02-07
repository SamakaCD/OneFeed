package com.ivansadovyi.data.feed

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feedImages")
class RoomFeedImage(
		@PrimaryKey
		var id: String,
		var itemId: String,
		var url: String,
		var width: Int?,
		var height: Int?
)
