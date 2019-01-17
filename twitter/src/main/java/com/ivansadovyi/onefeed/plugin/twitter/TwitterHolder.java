package com.ivansadovyi.onefeed.plugin.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;

public class TwitterHolder {

	private static TwitterHolder instance;
	private Twitter twitter;

	private TwitterHolder(Configuration configuration) {
		twitter = new TwitterFactory(configuration).getInstance();
	}

	public static TwitterHolder getInstance() {
		if (instance == null) {
			throw new IllegalStateException("TwitterHolder did not initialized with TwitterHolder.init(Configuration).");
		}

		return instance;
	}

	public static void init(Configuration configuration) {
		instance = new TwitterHolder(configuration);
	}

	public Twitter getTwitter() {
		return twitter;
	}
}
