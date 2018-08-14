package com.expr.journey;

import java.util.*;

public class Index<K,V> {
    private Map<K, V> index;

    public Index() {
        this(Collections.emptyList());
    }

    public Index(Collection<K> names) {
        this(names, names.size());
    }

    private Index(Collection<? extends Object> names, int size) {

        this.index = new LinkedHashMap<>(size);
        final Iterator<? extends Object> iterator = names.iterator();
        for (Integer i = 0; i < size; i++) {
            K name = (K) iterator.next();
            put(name, (V)i);
        }

    }

    public void put(K key, V value) {
        if (this.index.put(key, value) != null) {
            throw new RuntimeException(String.format("There is duplicate entry in the frame for key %s", key));
        }
    }

    public boolean contains(Object key) {
        return index.containsKey(key);
    }

    public V get(K key) {
        Object value = this.index.get(key);
        if (value == null) {
            throw new RuntimeException(String.format("Cannot find value for key %s", key));
        }
        return this.index.get(key);
    }

    public int size() {
        return this.index.size();
    }

    public Set<? extends K> keys() {
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
