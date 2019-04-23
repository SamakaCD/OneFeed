package com.ivansadovyi.onefeed.plugin.feedly.api;

import com.ivansadovyi.onefeed.plugin.feedly.Collection;
import com.ivansadovyi.onefeed.plugin.feedly.api.requests.ExchangeAuthCodeRequest;
import com.ivansadovyi.onefeed.plugin.feedly.api.responses.ExchangeAuthCodeResponse;
import com.ivansadovyi.onefeed.plugin.feedly.api.responses.StreamContentsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

	String URL = "https://sandbox7.feedly.com";
	String CLIENT_ID = "sandbox";
	String CLIENT_SECRET = "YTpe9mFmfeZvRSCB";

	@POST("/v3/auth/token")
	Call<ExchangeAuthCodeResponse> exchangeAuthCode(@Body ExchangeAuthCodeRequest request);

	@GET("/v3/collections")
	Call<List<Collection>> getCollections(@Header("Authorization") String authToken);

	@GET("/v3/streams/contents")
	Call<StreamContentsResponse> getStreamContents(
			@Header("Authorization") String authToken,
			@Query("streamId") String streamId,
			@Query("continuation") String continuation
	);
}
