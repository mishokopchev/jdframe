package com.expr.journey;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DataFrameTest {

    private BJUtil util;

    @Before
    public void setUp() {
        this.util = new BJUtil();
    }

    @Test
    public void testInitWithColumns() {

        List<String> names = Arrays.asList("Name", "Age", "Value");
        DataFrame df = util.withCols(names).build();
        Assert.assertTrue(df.columns().size() == names.size());
    }

    @Test
    public void testInitWithoutColumns() {
        List<String> names = Arrays.asList();
        DataFrame<?> df = new DataFrame(names);
        Assert.assertTrue(df.columns().size() == names.size());
    }

    @Test
    public void getColumnTest() {
        List<String> names = Arrays.asList("Name", "Age", "Value", "Column:1", "Column:2");
        DataFrame<?> df = util.withCols(names).withDefaultData().build();
        List nameColumn = df.getColumn("Name");
        List expected = Arrays.asList(0, 1, 2, 3, 4);
        Assert.assertEquals(nameColumn, expected);
    }

    @Test(expected = Exception.class)
    public void getWrongColumnTest() {
        List<String> names = Arrays.asList("Name", "Age", "Value", "Column:1", "Column:2");
        DataFrame<?> df = util.withCols(names).withDefaultData().build();
        List nameColumn = df.getColumn("Val"); // exception when retrieving not existing column
    }

    @Test
    public void addColumnTest() {
        String columns[] = {"Name", "Age", "Value", "Column:1", "Column:2"};
        DataFrame<?> df = util.withCols(columns).withDefaultData().build();

        String column = "Misho";
        df.addColumn(column);
        Object[] dfColumns = df.columns().toArray();

        for (int i = 0; i < columns.length; i++) {
            Assert.assertEquals(columns[i], dfColumns[i]);
        }


    }

    @Test
    public void getColumnWithoutValuesTest() {
        List<String> namesList = Arrays.asList("Name", "Age", "Value", "Column:1", "Column:2");
        DataFrame<?> df = util.withCols(namesList).withDefaultData().build();
        String newColumnName = "Misho";
        df.addColumn(newColumnName);

        List columnValues = df.getColumn(newColumnName);
        Assert.assertNotNull(columnValues);
        Object val = columnValues.get(0);
    }

    @Test
    public void getRowTest() {
        List<String> names = Arrays.asList("Name", "Age", "Value", "Column:1", "Column:2");
        DataFrame<?> df = util.withCols(names).withDefaultData().build();
        List row = df.getRow(1);
        Assert.assertEquals(row, Arrays.asList(1, 1, 1, 1, 1));

    }


}
