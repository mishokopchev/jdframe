package com.expr.journey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mihailkopchev on 8/12/18.
 */
public class BJUtil {

    private DataFrame dataFrame;
    private List columns = new ArrayList<>();
    private List<List> data = new ArrayList<>();


    public DataFrame empty() {
        dataFrame = new DataFrame();
        return dataFrame;
    }

    public BJUtil withCols(String... columns) {
        this.columns = Arrays.asList(columns);
        return this;
    }

    public BJUtil withCols(List columns) {
        this.columns = columns;
        return this;
    }

    public BJUtil withData(List<List> data) {
        this.data = data;
        return this;
    }

    public DataFrame build() {
        return new DataFrame(data, (List<Object>) columns);
    }

    public BJUtil withDefaultData() {
        List<List> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> lists = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                lists.add(j);
            }
            data.add(lists);
        }
        this.data = data;
        return this;
    }
}

