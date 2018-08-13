package com.expr.journey;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BlockTest {

    private Block block;
    private List<List<?>> data;

    @Before
    public void setUp() {
        List<List<?>> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> lists = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                lists.add(j);
            }
            data.add((List<Integer>) lists);
        }
        this.data = data;
        this.block = new Block(this.data);
    }

    @Test
    public void emptyInitTest() {
        Assert.assertEquals(new Block().length(), 0);
        Assert.assertEquals(new Block().width(), 0);
    }

    @Test
    public void testNOTNULL() {
        Assert.assertNotNull(this.block);
    }

    @Test
    public void lengthTest() {
        Assert.assertEquals(block.length(), data.get(0).size());
    }

    @Test
    public void widthTest() {
        Assert.assertEquals(this.block.width(), data.size());
    }

    @Test(expected = Exception.class)
    public void wrongRowColArguments() {
        this.block.put(10, 2, 12);
    }

    @Test
    public void putValueTest() {
        Integer expected = 12;
        this.block.put(1, 1, expected);
        Assert.assertEquals(block.get(1, 1), expected);
    }

    @Test(expected = Exception.class)
    public void wrongColArgument() {
        this.block.column(12);
    }

    @Test(expected = Exception.class)
    public void reshapeTestWithNull() {
        int row = 1;
        int col = 1;
        block.column(null);
        block.row(null);

    }

    @Test
    public void testAddingNullColumn() {
        int width = this.block.width();
        List newColumn = null;
        block.column(newColumn);
        Assert.assertEquals(block.width(), width + 1);
        Assert.assertEquals(block.column(width), data.get(width));
    }


    @Test
    public void testAddingNewColumnNotEnoughValues() {
        int currentWidth = block.width();
        int currentLength = block.length();

        List newColumn = new ArrayList();
        for (int i = 0; i < 5; i++) {
            newColumn.add(i);
        }
        block.column(newColumn);
        Assert.assertEquals(block.width(), currentWidth + 1);
        Assert.assertEquals(block.length(), currentLength);

    }


}
