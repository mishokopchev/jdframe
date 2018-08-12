package com.expr.journey;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * Created by mihailkopchev on 8/10/18.
 */
public abstract class IndexStorage<K> implements IIndexStorage<K> {
    private Index<K> storage;

    public abstract K defaultKey();

    public IndexStorage() {
        this(Collections.emptyList());
    }

    public IndexStorage(Collection collection) {
        this.storage = new Index<>(collection);
    }

    public void append(K key, int size) {
        requireNonNullOrThrowException(key);
        assert size > 0;
        storage.append(key, size);
    }

    @Override
    public void extend(int size) {
        if (size > 0) {
            storage.extend(size);
        }
    }

    @Override
    public boolean contains(K key) {
        requireNonNullOrThrowException(key);
        return storage.contains(key);
    }

    @Override
    public Object get(K key) {
        requireNonNullOrThrowException(key);
        return storage.get(key);
    }

    @Override
    public long size() {
        return storage.size();
    }

    @Override
    public Collection<K> keys() {
        return (Collection<K>) storage.names();
    }

    private void requireNonNullOrThrowException(Object obj) {
        Objects.requireNonNull(obj);
    }


}
