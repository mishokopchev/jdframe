package com.expr.journey;

import java.util.List;

/**
 * Created by mihailkopchev on 8/10/18.
 */
public interface IBlockService<V> {

    void reshape(int cols, int rows);

    void column(List<V> values);

    List<V> column(DataFrame df, int index);

    List<V> row(DataFrame df, int index);

    void row(List<V> vales);

    void put(int row, int col, V value);

    V get(int row, int col);

    int length();

    int width();


}
