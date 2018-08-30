package com.ivansadovyi.data.feed

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class RealmFeedItem(
		@PrimaryKey
		var id: String = "",
		var title: String = "",
		var content: String = "",
		var publicationDate: Date = Date(),
		var avatarImageUrl: String = ""
) : RealmObject()
