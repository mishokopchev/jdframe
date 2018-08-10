package com.expr.journey;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * Created by mihailkopchev on 8/10/18.
 */
public class IndexStorage<K> implements IIndexStorage<K> {
    private Index<K> storage;

    public IndexStorage() {
        this(Collections.emptyList());
    }

    public IndexStorage(Collection collection) {
        this.storage = new Index<>(collection);
    }

    @Override
    public void extend(int size) {
        if (size > 0) {
            storage.extend(size);
        }

    }

    @Override
    public void put(K key) {
        Objects.requireNonNull(key);
        storage.put(key);
    }

    @Override
    public boolean contains(K key) {
        Objects.requireNonNull(key);
        return storage.contains(key);
    }

    @Override
    public void put(K key, Object value) {
        Objects.requireNonNull(key);
        storage.put(key);
    }

    @Override
    public Object get(K key) {
        Objects.requireNonNull(key);
        return storage.get(key);
    }

    @Override
    public long size() {
        return storage.size();
    }

    @Override
    public Collection<K> names() {
        return (Collection<K>) storage.names();
    }


}
