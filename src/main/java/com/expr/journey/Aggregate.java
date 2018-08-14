package com.expr.journey;

import java.util.List;
import java.util.function.Function;

/**
 * Created by mihailkopchev on 8/14/18.
 */
public interface Aggregate<T extends Number, R> extends Function<List<T>, R> {

}
