package com.ivansadovyi.domain.feed.usecase

import com.ivansadovyi.domain.UseCase
import com.ivansadovyi.domain.feed.BundledFeedItem
import com.ivansadovyi.domain.feed.FeedItemRepository
import com.ivansadovyi.domain.feed.toBundledItem

class LikeFeedItemUseCase(
		private val item: BundledFeedItem,
		private val feedItemRepository: FeedItemRepository
) : UseCase<Unit> {

	override suspend fun execute() {
		println("is liked = ${item.isLiked}")
		item.builder
				.setLiked(!item.isLiked)
				.build()
				.toBundledItem(item.pluginClassName)
				.let { feedItemRepository.update(it); println("setting new state ${it.isLiked}") }
	}
}