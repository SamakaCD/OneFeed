package com.ivansadovyi.onefeed.plugin.twitter

import com.migcomponents.migbase64.Base64
import twitter4j.auth.AccessToken
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object AccessTokenSerializer {

	fun serialize(accessToken: AccessToken): String {
		val byteArrayStream = ByteArrayOutputStream()
		val objectStream = ObjectOutputStream(byteArrayStream)
		objectStream.writeObject(accessToken)
		return Base64.encodeToString(byteArrayStream.toByteArray(), false)
	}

	fun deserialize(serializedData: String): AccessToken {
		val byteArrayInputStream = ByteArrayInputStream(Base64.decode(serializedData))
		val objectStream = ObjectInputStream(byteArrayInputStream)
		return objectStream.readObject() as AccessToken
	}
}
