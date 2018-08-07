package com.expr.journey;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

public class Index {
	private Map<Object, Object> index;

	public Index() {
		this(Collections.emptyList());
	}

	public Index(Collection<? extends Object> names) {
		this(names, names.size());
	}

	public Index(Collection<? extends Object> names, int size) {
		this.index = new LinkedHashMap<>(size);
		final Iterator<? extends Object> iterator = names.iterator();
		for (int i = 0; i < size; i++) {
			Object name = iterator.hasNext() ? iterator.next() : i;
			add(name, i);
		}

	}

	public void extend(int size) {
		int currentSize = (int) size();
		int extendedWith = size + currentSize;

		for (int i = currentSize; i < extendedWith; i++) {
			add(i, i);
		}
	}

	public void add(Object key, Object value) {
		if (this.index.put(key, value) != null) {
			throw new RuntimeException(String.format("There is duplicate enrty in the frame for key %s", key));
		}
	}

	public void add(Object key) {
		add(key, index.size());
	}

	public Object get(Object key) {
		Object value = this.index.get(key);
		if (value == null) {
			throw new RuntimeException(String.format("Cannot find value for key %s", key));
		}
		return this.index.get(key);
	}

	public long size() {
		return this.index.size();
	}

	public Collection<? extends Object> names() {
		return this.index.keySet();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Object key : this.index.keySet()) {
			builder.append(key).append("\t");
		}
		return builder.toString();
	}
}
