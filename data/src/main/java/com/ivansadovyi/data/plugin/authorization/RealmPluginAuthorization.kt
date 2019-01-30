package com.ivansadovyi.data.plugin.authorization

import io.realm.RealmObject

open class RealmPluginAuthorization(
		var authorization: String = "",
		var pluginClassName: String = ""
) : RealmObject()
