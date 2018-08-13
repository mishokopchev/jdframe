package com.expr.journey;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class DataFrame<V> implements Iterable<List<V>> {
    private IBlockService<V> block;
    private IIndexStorage<String> columns;
    private IIndexStorage<Object> rows;


    public DataFrame(List<List<V>> data, Collection<Object> columns) {
        Collection indices = generateIndex(data);
        accurateParameters(data, columns, indices);
        this.block = new BlockService<>(data);
        this.columns = new Column(columns);
        this.rows = new Row(indices);

    }

    public DataFrame(String... columns) {
        this(Arrays.asList((Object[]) columns));
    }

    public DataFrame(List<Object> columns) {
        this(new ArrayList(), columns);
    }

    private Collection generateIndex(List<List<V>> data) {
        List<Object> indices = new ArrayList<>();
        if (!data.isEmpty()) {
            int len = data.get(0).size();
            for (int index = 0; index < len; index++) {
                indices.add(index);
            }
        }
        return indices;
    }

    private boolean accurateParameters(List<List<V>> data, Collection<Object> columns, Collection<Object> indices) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(columns);
        Objects.requireNonNull(indices);
        if (!data.isEmpty()) {
            if (data.size() != columns.size()) {
                throw new RuntimeException(
                        String.format("Shape of passed data is (%s), indices imply (%s)",
                                data.size(), columns.size()));
            }
        }

        return true;
    }


    public Collection<?> columns() {
        return this.columns.keys();
    }

    public DataFrame append(List<V> values) {

        return append(null, values);
    }

    public DataFrame append(Object name, List<V> values) {
        int len = (int) length();
        int row = len + 1;
        block.reshape(columns.keys().size(), row);
        block.row(values);
        rows.append(name, row);
        return this;
    }


    public DataFrame<V> addColumn(String column, List<V> values) {
        int len = this.columns.keys().size();
        columns.append(column, len + 1);
        block.column(values);
        return this;
    }

    public DataFrame<V> addColumn(String column) {
        return this.addColumn(column, null);
    }

    public List<V> getColumn(String column) {
        Integer index = (Integer) this.columns.get(column);
        return this.block.column(index);
    }

    public List<V> getRow(Object row) {
        Integer index = (Integer) this.rows.get(row);
        return this.block.row(index);
    }

    public V get(int row, int col) {
        return this.block.get(row, col);
    }

    public int length() {
        return block.length();
    }

    public int width() {
        return block.width();
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

