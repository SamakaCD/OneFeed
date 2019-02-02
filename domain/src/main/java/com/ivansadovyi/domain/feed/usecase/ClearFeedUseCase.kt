package com.ivansadovyi.domain.feed.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.feed.FeedItemRepository

class ClearFeedUseCase(private val feedItemRepository: FeedItemRepository) : UseCase<Unit> {

	override suspend fun execute() {
		feedItemRepository.clear()
	}
}
