package com.ivansadovyi.onefeed.plugin.feedly.api.responses;

import com.ivansadovyi.onefeed.plugin.feedly.FeedlyItem;

import java.util.List;

public class StreamContentsResponse {

	private List<FeedlyItem> items;

	public List<FeedlyItem> getItems() {
		return items;
	}
}
