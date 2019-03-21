package com.ivansadovyi.onefeed.plugin.feedly;

import java.util.List;

public class Collection {

	private String id;
	private String label;
	private List<Feed> feeds;

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public List<Feed> getFeeds() {
		return feeds;
	}
}
