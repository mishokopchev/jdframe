package com.expr.journey;

import java.util.Collection;

/**
 * Created by mihailkopchev on 8/10/18.
 */
public interface IIndexStorage<K> {

    void extend(int size);

    Object get(K key);

    long size();

    Collection<K> keys();

    boolean contains(K key);

    void append(K key, int size);

}
