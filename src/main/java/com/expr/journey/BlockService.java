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
        this(new Block<V>(data));
    }

    public BlockService(Block block) {
        this.block = block;
    }

    @Override
    public void reshape(int rows, int cols) {
        if (cols < 0 || rows < 0) {
            throw new IllegalArgumentException(String.format("(%s,%s) values exceed minimum indices", rows, cols));
        }
        for (int i = 0; i < cols; i++) {
            column(null);
        }
        for (int i = 0; i < rows; i++) {
            row(null);
        }

    }

    @Override
    public void column(List<V> values) {
        if (values == null) {
            values = new ArrayList<>();
        }

        int colLength = values.size();
        int blockLength = length();

        if (colLength == 0) {
            modifySize(values, 0, blockLength, false);
        } else if (colLength > blockLength) {
            modifySize(values, blockLength, colLength, true);
        } else if (colLength < blockLength) {
            modifySize(values, colLength, blockLength, false);
        }
        block.column(values);

    }

    @Override
    public void row(List<V> values) {
        if (values == null) {
            values = new ArrayList<>();
        }
        int len = length();
        int rowLen = values.size();
        int blockWidth = width();

        if (rowLen < blockWidth) {
            modifySize(values, rowLen, blockWidth, false);
        } else if (rowLen > len) {
            modifySize(values, len, rowLen, true);
        }
        // row_internal(len + 1, values);
        block.row(values);
    }

    private void row_internal(int row, List<V> values) {
        for (int c = 0; c < values.size(); c++) {
            block.put(row, c, values.get(c));
        }
    }

    @Override
    public List<V> column(DataFrame df, int index) {
        return new Views.SeriesBlockView(block, true, index);
    }

    @Override
    public List<V> row(DataFrame df, int index) {
        return new Views.SeriesBlockView<>(block, false, index);
    }

    @Override
    public void put(int row, int col, V value) {
        accurateIndices(row, col);
        block.put(row, col, value);
    }

    @Override
    public V get(int row, int col) {
        accurateIndices(row, col);
        return block.get(row, col);
    }


    @Override
    public int length() {
        return block.length();
    }

    public int width() {
        return block.width();
    }

    private boolean accurateIndices(int row, int col) {
        if ((col > this.width() || col < 0) || (row > this.length() || row < 0)) {
            throw new IllegalArgumentException(
                    String.format("(%s,%s) values exceed possible values of(%s,%s)", row, col, width(), length()));
        }
        return true;
    }

    private void modifySize(List struct, int from, int to, boolean shrink) {
        if (shrink) {
            for (int i = from; i < to; i++) {
                struct.remove(i);
            }
        } else {
            for (int i = from; i < to; i++) {
                struct.add(null);
            }
        }
    }
}
