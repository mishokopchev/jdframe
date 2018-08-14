package com.expr.journey;

import java.util.*;

public class DataFrame<V> implements Iterable<List<V>> {
    private IBlockService<V> block;
    private IIndexStorage columns;
    private IIndexStorage rows;
    private Grouping groups;


    public DataFrame(List<List<V>> data, Collection<Object> columns) {
        Collection indices = generateIndex(data);
        accurateParameters(data, columns, indices);
        this.block = new BlockService<>(data);
        this.columns = new Column(columns);
        this.rows = new Row(indices);

        this.groups = new Grouping();
    }

    public DataFrame(String... columns) {
        this(Arrays.asList((Object[]) columns));
    }

    public DataFrame(Collection<Object> columns) {
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

    public DataFrame appendRow(List<V> values) {

        return appendRow(null, values);
    }

    public DataFrame appendRow(Object name, List<V> values) {
        // tyko gore doly stava todo nqma li po dobra implementaciq no ako sega
        // smenq shte e baq catastrophic
        int row = 1;
        int col = 0;
        block.reshape(row, col);
        block.row(values);
        rows.put(name, length());
        return this;
    }


    public DataFrame<V> addColumn(String column, List<V> values) {
        int len = this.columns.keys().size();
        columns.put(column, len);
        block.column(values);
        return this;
    }

    public DataFrame<V> addColumn(String column) {
        return this.addColumn(column, null);
    }

    public List<V> getColumn(String column) {
        Integer index = (Integer) columns.get(column);
        return block.column(this, index);
    }

    public List<V> getRow(Object row) {
        Integer index = (Integer) rows.get(row);
        return block.row(this, index);
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
    public Iterator<List<V>> iterator() {
        DataFrame df = this;
        return null;
    }

    public DataFrame count(boolean skipNull) {
        return groups.apply(this, new Aggregation.Count<>(skipNull));
    }

    public DataFrame count() {
        return groups.apply(this, new Aggregation.Count<>());
    }

    public DataFrame min() {
        return groups.apply(this, new Aggregation.Min());
    }


}

