package com.ivansadovyi.data.plugin.authorization

import com.ivansadovyi.data.di.qualifiers.RealmScheduler
import com.ivansadovyi.domain.plugin.auth.PluginAuthorization
import com.ivansadovyi.domain.plugin.auth.PluginAuthorizationsDao
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealmPluginAuthorizationsDao @Inject constructor(
		private val realm: Realm,

		@RealmScheduler
		private val realmScheduler: Scheduler
) : PluginAuthorizationsDao {

	override fun getPluginAuthorizations(): Single<List<PluginAuthorization>> {
		return realm.where(RealmPluginAuthorization::class.java)
				.findAll()
				.asFlowable()
				.subscribeOn(realmScheduler)
				.firstOrError()
				.flatMapObservable { Observable.fromIterable(it) }
				.map { RealmPluginAuthorizationMapper.fromRealm(it) }
				.toList()
	}

	override fun putPluginAuthorization(pluginAuthorization: PluginAuthorization) {
		TODO()
	}
}
