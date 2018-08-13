package com.expr.journey;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mihailkopchev on 8/13/18.
 */
public class BlockServiceTest {

    private IBlockService blockService;

    @Before
    public void setUp() {
        blockService = new BlockService();
    }

    @Test
    public void testInit() {

    }

    @Test
    public void reshapeWithValidParamsTest() {
        int col = 1;
        int row = 2;
        blockService.reshape(row, col);
        Assert.assertEquals(blockService.width(), col);
        Assert.assertEquals(blockService.length(), row);
    }

    @Test(expected = IllegalArgumentException.class)
    public void reshapeWithInValidParamsTest() {
        blockService.reshape(-1, 10);

    }

    @Test
    public void addColumnTest() {
        blockService.column(null);
        blockService.column(null);
        Assert.assertEquals(blockService.width(), 2);
        Assert.assertEquals(blockService.length(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void putWrongIndicesTest() {
        blockService.put(-1, 10, 10);

    }

}
