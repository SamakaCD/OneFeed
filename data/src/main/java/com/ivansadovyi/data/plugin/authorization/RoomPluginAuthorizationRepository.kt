package com.ivansadovyi.data.plugin.authorization

import com.ivansadovyi.domain.plugin.auth.PluginAuthorization
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomPluginAuthorizationRepository(private val dao: RoomPluginAuthorizationDao) : PluginAuthorizationRepository {

	override suspend fun clear() = withContext(Dispatchers.IO) {
		dao.deleteAll()
	}

	override suspend fun getPluginAuthorizations(): List<PluginAuthorization> = withContext(Dispatchers.IO) {
		return@withContext dao.getAll().map(RoomPluginAuthorizationMapper::fromRoom)
	}

	override suspend fun putPluginAuthorization(pluginAuthorization: PluginAuthorization) = withContext(Dispatchers.IO) {
		dao.insert(RoomPluginAuthorizationMapper.toRoom(pluginAuthorization))
	}
}
