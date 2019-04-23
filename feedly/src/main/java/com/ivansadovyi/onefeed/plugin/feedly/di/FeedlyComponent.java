package com.ivansadovyi.onefeed.plugin.feedly.di;

import com.ivansadovyi.onefeed.plugin.feedly.FeedlyPlugin;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = FeedlyModule.class)
public interface FeedlyComponent {

	void inject(FeedlyPlugin feedlyPlugin);
}
