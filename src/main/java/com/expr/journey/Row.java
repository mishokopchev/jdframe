package com.expr.journey;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mihailkopchev on 8/10/18.
 */
public class Row extends IndexStorage<Object, Object> {
    private static Integer id = -1;

    public Row() {
        super();
    }

    public Row(Collection collection) {
        super(collection);
    }


    @Override
    public void put(Object key, Object size) {
        if (key == null) {
            key = defaultKey();
        }
        super.put(key, size);
    }

    @Override
    public Integer defaultKey() {
        id++;
        while (contains(id)) {
            id++;
        }
        return id;
    }

}
