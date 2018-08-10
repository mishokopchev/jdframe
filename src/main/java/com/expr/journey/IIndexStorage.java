package com.expr.journey;

import java.security.Key;
import java.util.Collection;
import java.util.List;

/**
 * Created by mihailkopchev on 8/10/18.
 */
public interface IIndexStorage<K> {

    void extend(int size);

    void put(K key);

    void put(K key, Object value);

    Object get(K key);

    long size();

    Collection<K> names();

    boolean contains(K key);


}
