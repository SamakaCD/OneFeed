package com.ivansadovyi.data.feed

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
		tableName = "feedImages",
		foreignKeys = [
			ForeignKey(
					entity = RoomFeedItem::class,
					parentColumns = ["id"],
					childColumns = ["itemId"],
					onDelete = ForeignKey.CASCADE
			)
		]
)
class RoomFeedImage(
		@PrimaryKey
		var id: String,
		var itemId: String,
		var url: String,
		var width: Int?,
		var height: Int?
)
