package com.ivansadovyi.domain.feed

import com.ivansadovyi.domain.utils.truba.Action

sealed class FeedAction : Action() {

	object LoadingStartedAction : FeedAction()

	object LoadingFinishedAction : FeedAction()

	object RefreshingStartedAction : FeedAction()

	object RefreshingFinishedAction : FeedAction()

	object RepositoryUpdateAction : FeedAction()
}