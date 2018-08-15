package com.expr.journey;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Created by mihailkopchev on 8/10/18.
 */
public abstract class IndexStorage<K, V> implements IIndexStorage<K, V> {
    private Index<K, V> storage;

    public abstract K defaultKey();

    public IndexStorage() {
        this(Collections.emptyList());
    }

    public IndexStorage(Collection collection) {
        this.storage = new Index<>(collection);
    }

    public void put(K key, V size) {
        requireNonNullOrThrowException(key);
        storage.put(key, size);
    }

    @Override
    public boolean contains(K key) {
        requireNonNullOrThrowException(key);
        return storage.contains(key);
    }

    @Override
    public V get(K key) {
        requireNonNullOrThrowException(key);
        return storage.get(key);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Set<K> keys() {
        return (Set<K>) storage.keys();
    }

    private void requireNonNullOrThrowException(Object obj) {
        Objects.requireNonNull(obj);
    }


    @Override
    public String toString() {
        return this.storage.toString();
    }
}
