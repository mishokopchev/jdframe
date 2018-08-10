package com.expr.journey;

import java.util.*;

public class Block<V> {

    private List<List<V>> blocks;
    private static List innerType = new ArrayList<>();

    public Block() {
        this(Collections.<List<V>>emptyList());
    }

    public Block(final Collection<? extends Collection<? extends V>> data) {
        if (data.isEmpty()) {
            this.blocks = new LinkedList<>();
            for (Collection<? extends V> col : data) {
                add(new ArrayList<V>(col));
            }
        } else {
            this.blocks = (List<List<V>>) data;
        }

    }

    public void put(int row, int col, V value) {
        blocks.get(col).set(row, value);
    }

    public V get(int row, int col) {
        return blocks.get(col).get(row);
    }

    public long size() {
        return size(0);
    }

    public long size(int index) {
        return blocks.isEmpty() ? 0 : blocks.get(0).size();
    }


    public void reshape(int withCols, int withRows) {
        for (int index = blocks.size(); index < withCols; index++) {
            List<V> column = innerType;
            this.add(column);
        }

        for (int index = 0; index < withRows; index++) {
            for (List<V> column : this.blocks) {
                column.add(null);
            }
        }
    }


    public void add(List<V> values) {
        if (values == null) {
            values = innerType;
            fill(values, 0, (int) this.size());
        } else if (values.size() > this.size()) {
            //todo Not Implemmented when you want more column with more values
        } else if (values.size() < this.size()) {
            fill(values, values.size(), (int) this.size());
        }
        this.blocks.add(values);
    }

    private void fill(List<V> column, int start, int end) {
        for (int i = start; i < end; i++) {
            column.add(null);
        }
    }

    @Override
    public String toString() {
        if (size() != 0) {
            StringBuilder builder = new StringBuilder("");
            for (int row = 0; row < size(); row++) {
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
