package com.expr.journey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mihailkopchev on 8/10/18.
 */
public class BlockService<V> implements IBlockService<V> {
    private Block<V> block;

    public BlockService() {
        block = new Block<V>();
    }

    public BlockService(final Collection<? extends Collection<? extends V>> data) {
        block = new Block<V>(data);
    }

    public BlockService(Block block) {
        this.block = block;
    }

    @Override
    public void reshape(int cols, int rows) {
        this.reshape(cols, rows);
    }

    @Override
    public boolean column(List<V> values) {
        if (values == null) {
            values = new ArrayList<>();
        }
        if (values.isEmpty()) {
            fill(values, 0, (int) this.size());
        } else if (values.size() > this.size()) {
            //reshapeAll todo
        } else if (values.size() < this.size()) {
            fill(values, values.size(), (int) this.size());
        }
        this.block.add(values);
        return true;
    }

    @Override
    public List<V> column(int index) {
        return null;
    }

    @Override
    public List<V> row(int index) {
        return null;
    }

    @Override
    public boolean row(List<V> values) {
        int row = 0;
        int c = 0;
        for (; c < values.size(); c++) {
            block.put(row, c, c < values.size() ? values.get(c) : null);
        }
        return true;
    }

    @Override
    public boolean put(int row, int col, V value) {
        if (col > this.size() || row > this.size()) {
            //exceptionche  todo
        }
        return this.block.put(row, col, value);
    }

    @Override
    public V get(int row, int col) {
        if (col > this.size() || row > this.size()) {
            //exceptionche todo
        }
        return block.get(row, col);
    }

    @Override
    public long size() {
        return block.size();
    }

    @Override
    public long size(int index) {
        //todo exception4e trqbva
        return index > block.size() ? 0 : block.size(index);
    }

    private void fill(List<V> column, int start, int end) {
        for (int i = start; i < end; i++) {
            column.add(null);
        }
    }
}
