package com.expr.journey;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DataFrameTest {

    @Test
    public void testInit() {

        List<String> names = Arrays.asList("Name", "Age", "Value");
        DataFrame<?> df = new DataFrame(names);
        Assert.assertTrue(df.columns().size() == names.size());

    }


}
