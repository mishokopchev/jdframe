package com.expr.journey;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFrameTest {

    private BJUtil util;

    @Before
    public void init() {
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

    public void addColumnTest() {
        List<String> namesList = Arrays.asList("Name", "Age", "Value", "Column:1", "Column:2");
        DataFrame<?> df = util.withCols(namesList).withDefaultData().build();
        String column = "Misho";
        df.addColumn(column);

        List<String> names = new ArrayList<>();
        namesList.forEach(element -> names.add(element));
        names.add(column);

        Assert.assertEquals(names, df.columns());
    }

    public void getColumnWithoutValuesTest() {
        List<String> namesList = Arrays.asList("Name", "Age", "Value", "Column:1", "Column:2");
        DataFrame<?> df = util.withCols(namesList).withDefaultData().build();
        String newColumnName = "Misho";
        df.addColumn(newColumnName);

        List columnValues = df.getColumn(newColumnName);
        Assert.assertNotNull(columnValues);
        List newColumnValues = df.getColumn(newColumnName);
        List empty = util.emptyListWithNulls(newColumnValues.size());
        Assert.assertEquals(df.getColumn(newColumnName), empty);

    }


    @Test
    public void getRowTest() {
        List<String> names = Arrays.asList("Name", "Age", "Value", "Column:1", "Column:2");
        DataFrame<?> df = util.withCols(names).withDefaultData().build();
        List row = df.getRow(1);
        Assert.assertEquals(row, null);

    }


}
