package com.expr.journey;

import java.util.*;

public class DataFrame<V> implements Iterable<List<V>> {
    private Block<V> data;
    private Index columns;
    private Index rows;
    private static String defaultColumnValue = "Column ";
    private static String defaultRowValue = "Row ";

    public DataFrame(Collection<Object> columns, Collection<Object> indexes) {
        this(Collections.<List<V>>emptyList(), columns, indexes);
    }

    public DataFrame(Block<V> data, Index columns, Index rows) {
        this.data = data;
        this.columns = columns;
        this.rows = rows;
    }

    public DataFrame(List<List<V>> data, Collection<Object> columns) {
        this(data, columns, Collections.emptyList());
    }

    public DataFrame(List<List<V>> data, Collection<Object> columns, Collection<Object> indexes) {
        this.data = new Block<>(data);
        this.columns = new Index(columns);
        this.rows = new Index(indexes);
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

    public DataFrame append(List<V> values) {

        return append(defaultRowValue + 1, values);
    }

    public DataFrame append(Object name, List<V> values) {
        int len = (int) size();
        int row = len + 1;
        this.data.reshape(columns.names().size(), row);
        this.rows.add(name);
        int c = 0;
        for (; c < values.size(); c++) {
            data.put(row, c, c < values.size() ? values.get(c) : null);
        }
        return this;
    }


    public DataFrame<V> addColumn(Object column, List<V> values) {
        this.data.add(values);
        this.columns.add(column);
        return this;
    }

    public DataFrame<V> addColumn(Object column) {
        this.addColumn(column, null);
        return this;
    }

    public long size() {
        return data.size();
    }

    @Override
    public String toString() {
        String cols = this.columns.toString();
        String blocks = this.data.toString();
        return new StringBuilder(cols).append("\n").append(blocks).toString();
    }

}
