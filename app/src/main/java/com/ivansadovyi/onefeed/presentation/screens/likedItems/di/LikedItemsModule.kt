package com.ivansadovyi.onefeed.presentation.screens.likedItems.di

import com.ivansadovyi.onefeed.presentation.GenericExceptionHandler
import com.ivansadovyi.onefeed.presentation.screens.likedItems.LikedItemsActivity
import dagger.Module
import dagger.Provides

@Module
abstract class LikedItemsModule {

	@Module
	companion object {

		@JvmStatic
		@Provides
		fun provideGenericExceptionHandler(activity: LikedItemsActivity): GenericExceptionHandler {
			return GenericExceptionHandler(activity)
		}
	}
}
