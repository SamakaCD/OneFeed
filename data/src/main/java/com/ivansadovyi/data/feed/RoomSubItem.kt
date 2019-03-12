package com.ivansadovyi.data.feed

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
		tableName = "subItems",
		foreignKeys = [
			ForeignKey(
					entity = RoomFeedItem::class,
					parentColumns = ["id"],
					childColumns = ["feedItemId"],
					onDelete = ForeignKey.CASCADE
			)
		]
)
class RoomSubItem(
		@PrimaryKey
		var id: String,
		var feedItemId: String,
		var title: String,
		var description: String?,

		@ColumnInfo(typeAffinity = ColumnInfo.BLOB)
		var icon: ByteArray?,
		var iconUrl: String?,
		var iconColor: Int?
)
