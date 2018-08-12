package com.expr.journey;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mihailkopchev on 8/10/18.
 */
public class Row extends IndexStorage<Object> {
    private static Integer id = 0;

    public Row() {
        super();
    }

    public Row(Collection collection) {
        super(collection);
    }

    private Object next() {
        return null;
    }

    @Override
    public void extend(int size) {
        super.extend(size);
    }

    @Override
    public void append(Object key, int size) {
        if (key == null) {
            key = defaultKey();
        }
        super.append(key, size);
    }

    @Override
    public Integer defaultKey() {
        id++;
        Set<Integer> keys = new HashSet(this.keys());
        while (keys.contains(id)) {
            id++;
        }
        return id;
    }

}
