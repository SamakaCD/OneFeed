package com.ivansadovyi.onefeed.plugin.feedly.di;

import com.ivansadovyi.onefeed.plugin.feedly.api.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class FeedlyModule {

	private final String apiUrl;

	public FeedlyModule(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	@Provides
	@Singleton
	public ApiService provideApiService() {
		return new Retrofit.Builder()
				.baseUrl(apiUrl)
				.addConverterFactory(GsonConverterFactory.create())
				.build()
				.create(ApiService.class);
	}
}
