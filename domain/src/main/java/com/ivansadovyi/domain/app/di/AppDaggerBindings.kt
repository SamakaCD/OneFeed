package com.ivansadovyi.domain.app.di

import com.ivansadovyi.domain.app.AppInteractor
import com.ivansadovyi.domain.app.AppInteractorImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppDaggerBindings {

	@Binds
	@Singleton
	abstract fun bindAppInteractor(impl: AppInteractorImpl): AppInteractor
}
