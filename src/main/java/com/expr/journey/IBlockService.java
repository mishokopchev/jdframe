package com.expr.journey;

import java.util.List;

/**
 * Created by mihailkopchev on 8/10/18.
 */
public interface IBlockService<V> {

    void reshape(int cols, int rows);

    boolean column(List<V> values);

    List<V> column(int index);

    List<V> row(int index);

    boolean row(List<V> vales);

    boolean put(int row, int col, V value);

    V get(int row, int col);

    int length();

    int length(int index);

    int width();


}
