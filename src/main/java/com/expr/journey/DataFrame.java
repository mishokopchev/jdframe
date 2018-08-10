package com.expr.journey;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class DataFrame<V> implements Iterable<List<V>> {
    private IBlockService<V> block;
    private IIndexStorage<String> columns;
    private IIndexStorage<Object> rows;

    public DataFrame(Collection<Object> columns, Collection<Object> indexes) {
        this(Collections.<List<V>>emptyList(), columns, indexes);
    }

    public DataFrame(List<List<V>> data, Collection<Object> columns) {
        this(data, columns, Collections.emptyList());
    }

    public DataFrame(List<List<V>> data, Collection<Object> columns, Collection<Object> indexes) {
        this.block = new BlockService<>(data);
        this.columns = new Column(columns);
        this.rows = new Row(indexes);
    }

    public DataFrame(String... columns) {
        this(Arrays.asList((Object[]) columns));
    }

    public DataFrame(List<Object> columns) {
        this(Collections.<List<V>>emptyList(), columns, Collections.emptyList());
    }


    public Collection<?> columns() {
        return this.columns.names();
    }

    public DataFrame append(List<V> values) {

        return append(null, values);
    }

    public DataFrame append(Object name, List<V> values) {
        int len = (int) size();
        int row = len + 1;
        this.block.reshape(columns.names().size(), row);

        this.rows.put(name);
        int c = 0;

        this.block.row(values);
        return this;
    }


    public DataFrame<V> column(String column, List<V> values) {
        this.block.column(values);
        this.columns.put(column);
        return this;
    }

    public DataFrame<V> column(String column) {
        this.column(column, null);
        return this;
    }

    public long size() {
        return block.size();
    }

    @Override
    public String toString() {
        String cols = this.columns.toString();
        String blocks = this.block.toString();
        return new StringBuilder(cols).append("\n").append(blocks).toString();
    }

    @Override
    public Iterator<List<V>> iterator() {
        throw new NotImplementedException();
    }

}
