package com.expr.journey;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by mihailkopchev on 8/14/18.
 */
public class Grouping<V> {

    public DataFrame apply(DataFrame df, Function function) {
        Iterator<List<V>> iterator = df.iterator();
        DataFrame<Integer> dataFrame = new DataFrame(df.columns());
        for (List<Integer> m : dataFrame) {

        }
        return null;
    }

}
