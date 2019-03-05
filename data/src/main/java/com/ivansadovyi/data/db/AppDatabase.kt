package com.ivansadovyi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ivansadovyi.data.db.converters.RoomDateConverter
import com.ivansadovyi.data.db.converters.RoomFeedItemPriorityConverter
import com.ivansadovyi.data.feed.*
import com.ivansadovyi.data.plugin.authorization.RoomPluginAuthorization
import com.ivansadovyi.data.plugin.authorization.RoomPluginAuthorizationDao

@Database(
		entities = [
			RoomFeedItem::class,
			RoomFeedImage::class,
			RoomSubItem::class,
			RoomPluginAuthorization::class
		],
		version = 1
)
@TypeConverters(RoomDateConverter::class, RoomFeedItemPriorityConverter::class)
abstract class AppDatabase : RoomDatabase() {

	abstract fun getFeedItemDao(): RoomFeedItemDao

	abstract fun getFeedImageDao(): RoomFeedImageDao

	abstract fun getSubItemDao(): RoomSubItemDao

	abstract fun getPluginAuthorizationDao(): RoomPluginAuthorizationDao

	companion object {
		const val NAME = "app_database"
	}
}
