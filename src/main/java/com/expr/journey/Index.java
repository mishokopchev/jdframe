package com.expr.journey;

import java.util.*;

public class Index<K extends Object> {
    private Map<K, Object> index;
    private K defaultKey;

    public Index() {
        this(Collections.emptyList());
    }

    public Index(Collection<? extends Object> names) {
        this(names, names.size());
    }

    public Index(Collection<? extends Object> names, int size) {
        this.index = new LinkedHashMap<>(size);
        final Iterator<? extends Object> iterator = names.iterator();
        for (int i = 0; i < size; i++) {
            K name = iterator.hasNext() ? (K) iterator.next() : defaultKey;
            put(name, i);
        }

    }

    public void extend(int size, K defaultKey) {
        int currentSize = (int) size();
        int extendedWith = size + currentSize;
        for (int i = currentSize; i < extendedWith; i++) {
            put(defaultKey, i);
        }
    }

    public void put(K key, Object value) {
        if (this.index.put(key, value) != null) {
            throw new RuntimeException(String.format("There is duplicate enrty in the frame for key %s", key));
        }
    }

    public boolean contains(Object key) {
        return index.containsKey(key);
    }

    public void put(K key) {
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
