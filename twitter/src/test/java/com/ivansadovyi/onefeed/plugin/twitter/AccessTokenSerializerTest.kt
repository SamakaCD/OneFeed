package com.ivansadovyi.onefeed.plugin.twitter

import org.junit.Assert
import org.junit.Test
import twitter4j.auth.AccessToken

class AccessTokenSerializerTest {

	@Test
	fun test() {
		val accessToken = AccessToken("token", "tokenSecret")
		val serializedAccessToken = AccessTokenSerializer.serialize(accessToken)
		val deserializedAccessToken = AccessTokenSerializer.deserialize(serializedAccessToken)
		Assert.assertEquals(accessToken, deserializedAccessToken)
	}
}