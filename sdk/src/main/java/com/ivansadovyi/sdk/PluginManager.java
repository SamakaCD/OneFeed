package com.ivansadovyi.sdk;

import java.util.List;

import androidx.annotation.NonNull;

public interface PluginManager {

	List<OneFeedPluginDescriptor> getAuthorizedPluginDescriptors();

	void startAuthorization(@NonNull OneFeedPluginDescriptor descriptor);
}
