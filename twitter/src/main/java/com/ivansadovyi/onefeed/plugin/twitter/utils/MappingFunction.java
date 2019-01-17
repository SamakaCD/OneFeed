package com.ivansadovyi.onefeed.plugin.twitter.utils;

public interface MappingFunction<T, R> {

	R map(T input);
}
