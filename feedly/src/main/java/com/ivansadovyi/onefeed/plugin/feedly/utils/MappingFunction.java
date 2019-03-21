package com.ivansadovyi.onefeed.plugin.feedly.utils;

public interface MappingFunction<T, R> {

	R map(T input);
}
