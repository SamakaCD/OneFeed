package com.ivansadovyi.onefeed.plugin.twitter;

import com.migcomponents.migbase64.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import twitter4j.auth.AccessToken;

public class AccessTokenSerializer {

	public static String serialize(AccessToken accessToken) throws IOException {
		ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
		ObjectOutputStream objectStream = new ObjectOutputStream(byteArrayStream);
		objectStream.writeObject(accessToken);
		byte[] accessTokenBytes = byteArrayStream.toByteArray();
		return Base64.encodeToString(accessTokenBytes, false);
	}

	public static AccessToken deserialize(String serializedData) throws IOException, ClassNotFoundException {
		byte[] accessTokenBytes = Base64.decode(serializedData);
		ByteArrayInputStream byteArrayStream = new ByteArrayInputStream(accessTokenBytes);
		ObjectInputStream objectStream = new ObjectInputStream(byteArrayStream);
		return (AccessToken) objectStream.readObject();
	}
}
