package com.ivansadovyi.data.plugin.authorization

import com.ivansadovyi.data.di.qualifiers.RealmScheduler
import com.ivansadovyi.domain.plugin.auth.PluginAuthorization
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationsDao
import io.realm.Realm
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealmPluginAuthorizationsDao @Inject constructor(
		private val realm: Realm,

		@RealmScheduler
		private val coroutineDispatcher: CoroutineDispatcher
) : PluginAuthorizationsDao {

	override suspend fun getPluginAuthorizations(): List<PluginAuthorization> = withContext(coroutineDispatcher) {
		return@withContext realm.where(RealmPluginAuthorization::class.java)
				.findAll()
				.map(RealmPluginAuthorizationMapper::fromRealm)
	}

	override suspend fun putPluginAuthorization(pluginAuthorization: PluginAuthorization) = withContext(coroutineDispatcher) {
		realm.executeTransaction {
			realm.copyToRealm(RealmPluginAuthorizationMapper.toRealm(pluginAuthorization))
		}
	}
}
