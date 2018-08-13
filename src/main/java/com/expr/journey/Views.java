package com.expr.journey;

import java.util.AbstractList;

/**
 * Created by mihailkopchev on 8/13/18.
 */
public class Views {
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
            return transpose ? dataFrame.get(ind, this.index) : dataFrame.get(this.index, ind);
        }

        @Override
        public int size() {
            return transpose ? dataFrame.width() : dataFrame.length();

        }
    }

    public static class SeriesBlockView<V> extends AbstractList<V> {
        private Block<V> block;
        private boolean transpose;
        private int index;

        public SeriesBlockView(Block<V> block, boolean transpose, int index) {
            this.transpose = transpose;
            this.index = index;
            this.block = block;
        }

        @Override
        public V get(int ind) {
            return transpose ? block.get(ind,this.index) : block.get(this.index, ind);
        }

        @Override
        public int size() {
            return transpose ? block.width() : block.length();

        }
    }
}
