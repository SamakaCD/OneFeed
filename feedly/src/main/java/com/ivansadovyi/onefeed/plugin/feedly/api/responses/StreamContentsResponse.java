package com.ivansadovyi.onefeed.plugin.feedly.api.responses;

import com.ivansadovyi.onefeed.plugin.feedly.FeedlyItem;

import java.util.List;

public class StreamContentsResponse {

	private String continuation;
	private List<FeedlyItem> items;

	public String getContinuation() {
		return continuation;
	}

	public List<FeedlyItem> getItems() {
		return items;
	}
}
