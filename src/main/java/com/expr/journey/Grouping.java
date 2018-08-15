package com.expr.journey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Created by mihailkopchev on 8/14/18.
 */
public class Grouping<V> {

    public <V> DataFrame<V> apply(DataFrame<V> df, Function<?, ?> function) {

        if (df.isEmpty()) {
            return df;
        }

        Collection columns = df.columns();
        List<List<V>> grouped = setUp(columns.size());
        List aggregated = new ArrayList<>();


        for (List column : df) {
            if (function instanceof Aggregate) {
                Object result = Aggregate.class.cast(function).apply(column);
                aggregated.add(result);
            }

        }
        for (int i = 0; i < aggregated.size(); i++) {
            grouped.get(i).add((V) aggregated.get(i));
        }

        return new DataFrame<>(grouped, columns);
    }

    private <V> List setUp(int with) {
        List<List<V>> main = new ArrayList<>();
        for (int i = 0; i < with; ++i) {
            main.add(new ArrayList());
        }
        return main;
    }

}
