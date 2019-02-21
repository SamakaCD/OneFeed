package com.ivansadovyi.domain.feed.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.feed.FeedItemRepository
import com.ivansadovyi.domain.log.LoggingInteractor

class ClearFeedUseCase(
		private val loggingInteractor: LoggingInteractor,
		private val feedItemRepository: FeedItemRepository
) : UseCase<Unit> {

	override suspend fun execute() {
		loggingInteractor.debug(this@ClearFeedUseCase, "Clearing feed")
		feedItemRepository.clear()
	}
}
