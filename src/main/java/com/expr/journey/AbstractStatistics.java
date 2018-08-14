package com.expr.journey;

import java.util.List;

/**
 * Created by mihailkopchev on 8/14/18.
 */
public class AbstractStatistics {
    public static class Mean implements Statistic {
        private List<Number> numbers;
        private Double result;
        private int len = 0;

        @Override
        public void increment(Number value) {
            if (value != null) {
                result += (Double) value;
                len++;
            }
        }

        @Override
        public Number getValue() {
            return result / len;
        }
    }

    public static class Min implements Statistic {
        private Double result;

        @Override
        public void increment(Number value) {
            if (value != null) {
                if (result < (Double) value) {
                    result = (Double) value;
                }

            }
        }

        @Override
        public Number getValue() {
            return result;
        }
    }
}
