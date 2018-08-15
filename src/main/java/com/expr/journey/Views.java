package com.expr.journey;

import java.util.AbstractList;
import java.util.List;

/**
 * Created by mihailkopchev on 8/13/18.
 */
public class Views {

    public static class ListView<V> extends AbstractList<List<V>> {
        private DataFrame dataFrame;
        private boolean transpose;

        public ListView(DataFrame df, boolean transpose) {
            this.dataFrame = df;
            this.transpose = transpose;
        }

        @Override
        public List<V> get(int index) {
            return new SeriesListView<>(dataFrame, transpose, index);
        }

        @Override
        public int size() {
            return transpose ? dataFrame.length() : dataFrame.width();
        }
    }

    public static class SeriesListView<V> extends AbstractList<V> {
        private DataFrame<V> dataFrame;
        private boolean transpose;
        private int index;

        public SeriesListView(DataFrame dataFrame, boolean transpose, int index) {
            this.dataFrame = dataFrame;
            this.transpose = transpose;
            this.index = index;
        }

        @Override
        public V get(int ind) {
            return transpose ? dataFrame.get(this.index, ind) : dataFrame.get(ind, this.index);
        }

        @Override
        public int size() {
            return transpose ? dataFrame.length() : dataFrame.width();

        }
    }

    public static class SeriesBlockView<V> extends AbstractList<V> {
        private Block<V> block;
        private boolean transpose;
        private int index;

        public SeriesBlockView(Block<V> block, boolean transpose, int index) {
            this.block = block;
            this.transpose = transpose;
            this.index = index;
        }

        @Override
        public V get(int ind) {
            return transpose ? block.get(this.index, ind) : block.get(ind, this.index);
        }

        @Override
        public int size() {
            return transpose ? block.width() : block.length();

        }
    }
}
