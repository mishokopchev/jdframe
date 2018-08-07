package com.expr.journey;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DataFrame<V> implements Iterable<List<V>> {
	private Block<V> data;
	private Index columns;
	private Index index;

	public DataFrame(Collection<Object> columns, Collection<Object> indexes) {
		this(Collections.<List<V>>emptyList(), columns, indexes);
	}

	public DataFrame(Block<V> data, Index columns, Index index) {
		this.data = data;
		this.columns = columns;
		this.index = index;
	}

	public DataFrame(List<List<V>> data, Collection<Object> columns) {
		this(data, columns, Collections.emptyList());
	}

	public DataFrame(List<List<V>> data, Collection<Object> columns, Collection<Object> indexes) {
		this.data = new Block<>(data);
		this.columns = new Index(columns);
		this.index = new Index(indexes);
	}

	public DataFrame(String... columns) {
		this(Arrays.asList((Object[]) columns));
	}

	public DataFrame(List<Object> columns) {
		this(Collections.<List<V>>emptyList(), columns, Collections.emptyList());
	}

	@Override
	public Iterator<List<V>> iterator() {
		return null;
	}

	public Collection<?> columns() {
		return this.columns.names();
	}

	public DataFrame add(Object column, List<V> values) {
		this.columns.add(column);
		this.data.add(values);
		return this;
	}

	@Override
	public String toString() {
		String cols = this.columns.toString();
		String data = this.data.toString();
		return new StringBuilder(cols).append("\n").append(data).toString();
	}

}
