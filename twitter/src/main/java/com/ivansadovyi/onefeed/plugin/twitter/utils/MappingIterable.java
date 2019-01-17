package com.ivansadovyi.onefeed.plugin.twitter.utils;

import java.util.Iterator;

import androidx.annotation.NonNull;

public class MappingIterable<T, R> implements Iterable<R> {

	private final MappingIterator<T, R> mappingIterator;

	public MappingIterable(Iterable<T> sourceIterable, MappingFunction<T, R> mappingFunction) {
		mappingIterator = new MappingIterator<>(sourceIterable.iterator(), mappingFunction);
	}

	@NonNull
	@Override
	public Iterator<R> iterator() {
		return mappingIterator;
	}

	private static class MappingIterator<T, R> implements Iterator<R> {

		private final Iterator<T> sourceIterator;
		private final MappingFunction<T, R> mappingFunction;

		public MappingIterator(Iterator<T> sourceIterator, MappingFunction<T, R> mappingFunction) {
			this.sourceIterator = sourceIterator;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public boolean hasNext() {
			return sourceIterator.hasNext();
		}

		@Override
		public R next() {
			T nextItem = sourceIterator.next();
			return mappingFunction.map(nextItem);
		}
	}
}
