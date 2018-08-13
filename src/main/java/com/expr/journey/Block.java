package com.expr.journey;

import java.util.*;

public class Block<V> {

    private List<List<V>> blocks;

    public Block() {
        this(Collections.<List<V>>emptyList());
    }

    public Block(final Collection<? extends Collection<? extends V>> data) {
        if (data.isEmpty()) {
            this.blocks = new LinkedList<>();
            for (Collection<? extends V> col : data) {
                column(new ArrayList<V>(col));
            }
        } else {
            this.blocks = (List<List<V>>) data;
        }
        //todo column column si gi obarkal mnogo si razseqn bratche pochini i togava go opravi tova
    }

    public void put(int row, int col, V value) {
        blocks.get(col).set(row, value);
    }

    public V get(int row, int col) {
        return blocks.get(col).get(row);
    }

    public List<V> column(int index) {
        return this.blocks.get(index);
    }

    public void column(List<V> values) {
        this.blocks.add(values);
    }

    public void row(List<V> values) {
        int index = 0;
        for (List block : blocks) {
            block.add(values.get(index++));
        }

    }

    public int length() {
        return length(0);
    }

    public int length(int index) {
        return blocks.isEmpty() ? 0 : blocks.get(index).size();
    }

    public int width() {
        return blocks.isEmpty() ? 0 : blocks.size();
    }

    @Override
    public String toString() {
        if (length() != 0) {
            StringBuilder builder = new StringBuilder("");
            for (int row = 0; row < length(); row++) {
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
