package com.expr.journey;

import java.util.Collection;

/**
 * Created by mihailkopchev on 8/10/18.
 */
public class Row extends IndexStorage<Object> {
    private static Integer id = 0;

    public Row(Collection collection) {
        super(collection);
    }

    @Override
    public Integer defaultKey() {
        id++;
        Collection<Object> keys = this.names();
        if (keys.contains(id)) {
            id = defaultKey();
            return id;
        }
        return id;
    }
}
