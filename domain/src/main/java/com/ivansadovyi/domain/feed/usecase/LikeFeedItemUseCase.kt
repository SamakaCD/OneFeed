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
		item.builder
				.setLiked(!item.isLiked)
				.setLikesCount(getNewLikesCount())
				.build()
				.toBundledItem(item.pluginClassName)
				.let { feedItemRepository.update(it) }
	}

	private fun getNewLikesCount(): Int {
		return if (item.isLiked) {
			item.likesCount - 1
		} else {
			item.likesCount + 1
		}
	}
}