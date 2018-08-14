package com.expr.journey;

import java.util.Set;

/**
 * Created by mihailkopchev on 8/10/18.
 */
public interface IIndexStorage<K,V> {

    V get(K key);

    int size();

    Set<K> keys();

    boolean contains(K key);

    void put(K key, V size);

}
