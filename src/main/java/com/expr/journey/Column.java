package com.expr.journey;

import java.util.Collection;

/**
 * Created by mihailkopchev on 8/10/18.
 */
public class Column extends IndexStorage<String, Integer> {
    private static String defaultKey = "Column: ";
    private static Integer counter = 0;

    public Column(Collection collection) {
        super(collection);
    }

    @Override
    public String defaultKey() {
        counter++;
        return defaultKey + counter;
    }

    @Override
    public void put(String key, Integer size) {
        if (key == null) {
            key = defaultKey();
        }
        super.put(key, size);
    }
}
