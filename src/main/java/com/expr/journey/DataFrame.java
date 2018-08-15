package com.expr.journey;

import java.util.*;
import java.util.function.Function;

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

    private DataFrame(IBlockService service, Collection columns) {

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

    private ListIterator<List<V>> iterrows() {
        return new Views.ListView(this, false).listIterator();
    }

    private ListIterator<List<V>> itercols() {
        return new Views.ListView(this, false).listIterator();
    }

    @Override
    public Iterator<List<V>> iterator() {
        return itercols();
    }


    public DataFrame count(boolean skipNull) {
        return groups.apply(this, new Aggregation.Count<>(skipNull));
    }

    public DataFrame count() {
        return groups.apply(this, new Aggregation.Count<>());
    }

    public DataFrame sum(){
        return groups.apply(this,new Aggregation.Sum<>(new AbstractStatistics.Sum()));
    }

    public DataFrame min() {
        return groups.apply(this, new Aggregation.Min());
    }

    public DataFrame apply(Function function, Axis axis) {
        // recome with the apply methods here
        return null;
    }

    public DataFrame concat() {
        return this;

    }

    public DataFrame merge() {
        return this;
    }

    // the function to work
    public enum Axis {
        ROW, // rowsie0
        COL // 1 col wise
    }

    public DataFrame shape() {
        return null;
    }

    public List<V> dtypes() {
        return null;
    }

    public boolean isEmpty() {
        return this.length() == 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.columns);
        builder.append("\n");
        builder.append(this.block);
        return builder.toString();
    }
}

