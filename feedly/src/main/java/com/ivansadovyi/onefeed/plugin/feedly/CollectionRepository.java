package com.ivansadovyi.onefeed.plugin.feedly;

import com.ivansadovyi.onefeed.plugin.feedly.api.ApiService;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CollectionRepository {

	private static final String DEFAULT_COLLECTION_LABEL = "Default";

	private ApiService apiService;
	private AuthTokenRepository authTokenRepository;
	private Collection defaultCollection;

	@Inject
	public CollectionRepository(ApiService apiService, AuthTokenRepository authTokenRepository) {
		this.apiService = apiService;
		this.authTokenRepository = authTokenRepository;
	}

	public Collection getDefaultCollection() throws IOException {
		if (defaultCollection == null) {
			List<Collection> collections = apiService.getCollections(authTokenRepository.getAuthToken()).execute().body();
			defaultCollection = findDefaultCollection(collections);
		}

		return defaultCollection;
	}

	private Collection findDefaultCollection(List<Collection> collections) {
		if (collections.isEmpty()) {
			throw new IllegalArgumentException("Can not get default collection from empty list");
		}

		for (Collection collection : collections) {
			if (DEFAULT_COLLECTION_LABEL.equals(collection.getLabel())) {
				return collection;
			}
		}

		// Return first collection as fallback
		return collections.get(0);
	}
}
