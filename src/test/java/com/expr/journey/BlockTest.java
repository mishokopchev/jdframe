package com.expr.journey;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockTest {

    private Block block;
    private List<List<?>> data;


    @Before
    public void initData() {
        List<List<?>> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> lists = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                lists.add(j);
            }
            data.add((List<Integer>) lists);
        }
        this.data = data;
    }


    @Before
    public void initBlock() {
        this.block = new Block(this.data);

    }

    @Test
    public void testNOTNULL() {
        Assert.assertNotNull(this.block);
    }

    @Test
    public void testSize() {
        Assert.assertEquals(this.block.size(), data.size());
    }

    @Test
    public void testAddingNullColumn() {
        List newColumn = null;
        block.add(newColumn);
        data.add(newColumn);
        Assert.assertEquals(block.size(), data.get(0).size());
    }

    @Test
    public void testAddingNewCollumnNotEnoughValues() {
        List newColumn = new ArrayList();
        newColumn.add(1);
        newColumn.add(1);
        newColumn.add(1);

        block.add(newColumn);
        data.add(newColumn);
        Assert.assertEquals(block.size(5),block.size());

    }

    // check if all the List<V> in the main List has the same length
    public void testAccuracy() {
        // reflection to check the whole structure
    }


}
