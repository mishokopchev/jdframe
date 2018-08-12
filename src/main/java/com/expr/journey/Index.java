package com.expr.journey;

import java.util.*;

public class Index<K> {
    private Map<K, Integer> index;


    public Index() {
        this(Collections.emptyList());
    }

    public Index(Collection<K> names) {
        this(names, names.size());
    }

    private Index(Collection<? extends Object> names, int size) {
        assert names.size() == size;
        this.index = new LinkedHashMap<>(size);
        final Iterator<? extends Object> iterator = names.iterator();
        for (int i = 0; i < size; i++) {
            K name = (K) iterator.next();
            put(name, i);
        }

    }

    public void append(K key, int size) {
        put(key, size);
    }

    public void extend(int size) {
        int currentSize = (int) size();
        int extendedWith = size + currentSize;
        for (Integer i = currentSize; i < extendedWith; i++) {
            put((K) i, i);
        }
    }

    public void put(K key, Integer value) {
        if (this.index.put(key, value) != null) {
            throw new RuntimeException(String.format("There is duplicate enrty in the frame for key %s", key));
        }
    }

    public boolean contains(Object key) {
        return index.containsKey(key);
    }

    private void put(K key) {
        put(key, index.size());
    }

    public Object get(Object key) {
        Object value = this.index.get(key);
        if (value == null) {
            throw new RuntimeException(String.format("Cannot find value for key %s", key));
        }
        return this.index.get(key);
    }

    public long size() {
        return this.index.size();
    }

    public Collection<? extends K> names() {
        return this.index.keySet();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Object key : this.index.keySet()) {
            builder.append(key).append("\t");
        }
        return builder.toString();
    }
}
