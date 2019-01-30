package com.ivansadovyi.domain.feed.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.feed.FeedItemsDao

class ClearFeedUseCase(private val feedItemsDao: FeedItemsDao) : UseCase<Unit> {

	override suspend fun execute() {
		feedItemsDao.clear()
	}
}
