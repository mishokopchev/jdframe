package com.expr.journey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Block<V> {

	private List<List<V>> blocks;

	public Block() {
		this(Collections.<List<V>>emptyList());
	}

	public Block(final Collection<? extends Collection<? extends V>> data) {
		if (data.isEmpty()) {
			this.blocks = new LinkedList<>();
			for (Collection<? extends V> col : data) {
				add_internal(new ArrayList<V>(col));
			}
		} else {
			this.blocks = (List<List<V>>) data;
		}

	}

	public void put(int col, int row, V value) {
		blocks.get(col).set(row, value);
	}

	public V get(int row, int col) {
		return blocks.get(col).get(row);
	}

	public long size() {
		return blocks.isEmpty() ? 0 : blocks.get(0).size();
	}

	public void add(List<V> values) {
		if (values != null && !values.isEmpty()) {
			blocks.add(values);
		} else {
			add_internal(values);
		}
	}

	private void add_internal(List<V> column) {
		if (column == null) {
			column = new ArrayList<>();
		}
		final long size = size();
		for (int i = 0; i < size; i++) {
			column.add(null);
		}
		blocks.add(column);
	}

	@Override
	public String toString() {
		if (size() != 0) {
			StringBuilder builder = new StringBuilder();
			for(int row = 0; row < size();row++) {
				for (int index = 0; index < blocks.size(); index++) {
					builder.append(blocks.get(index).get(row));
					builder.append("\t");
				}
				builder.append("\n");
			}
			return builder.toString();
		}

		return "Block [blocks=" + blocks + "]";
	}

}
