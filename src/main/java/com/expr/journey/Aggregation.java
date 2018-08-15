package com.expr.journey;

import java.util.List;

/**
 * Created by mihailkopchev on 8/14/18.
 */
public class Aggregation {

    public static class Count<V> implements Aggregate<V, Number> {
        private boolean skipNa;

        public Count() {
            this(false);
        }

        public Count(boolean skippNullable) {
            this.skipNa = skippNullable;
        }

        public Number apply(List<V> vs) {
            if (skipNa) {
                int count = (int) vs.stream().filter(element -> element != null ? true : false).count();
                return count;
            } else {
                return new Integer(vs.size());
            }

        }
    }

    public static class AbstractAggregation<V extends Number> implements Aggregate<V, Number> {
        private Statistic statistic;

        public AbstractAggregation(Statistic statistic) {
            this.statistic = statistic;
        }

        @Override
        public Number apply(List<V> lists) {
            for (Number element : lists) {
                statistic.increment(element);
            }
            return statistic.getValue();
        }
    }

    public static class Sum<V extends Number> extends AbstractAggregation<V> {

        public Sum(Statistic statistic) {
            super(statistic);
        }

        @Override
        public Number apply(List<V> vs) {
            Double d = 0.0;
            for (Object obj : vs) {
                if ((obj instanceof Number)) {
                    d += Number.class.cast(obj).doubleValue();
                } else {
                    throw new RuntimeException(String.format("Value with class %s not acceptable to class Number", obj.getClass()));
                }
            }
            return d;
        }
    }

    public static class Product<V extends Number> implements Aggregate<V, Number> {

        @Override
        public Number apply(List<V> vs) {
            Double d = 0.0;
            for (Object obj : vs) {
                if ((obj instanceof Number)) {
                    d *= Number.class.cast(obj).doubleValue();
                } else {
                    throw new RuntimeException(String.format("Value with class %s not acceptable to class Number", obj.getClass()));
                }
            }
            return d;
        }
    }

    public static class Mean {
        //this on delete on n
    }

    public static class Variance {
        // use the mean and then delete on
    }

    public static class StandardDeviation {
        // sqrt from variance
    }

    public static class Percentile {
        //do not know it fucked up a little
    }

    public static class Min extends AbstractAggregation {

        public Min() {
            super(new AbstractStatistics.Min());
        }


        // like max
    }

    public static class Max {
        // like min
    }


}
