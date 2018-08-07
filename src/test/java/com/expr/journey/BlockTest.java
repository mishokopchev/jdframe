package com.expr.journey;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class BlockTest {

	@Test
	public void testInit() {
		Block<String> block = new Block();
		long l = 0;
		Assert.assertEquals(block.size(), l);
	}

	@Test
	public void testCreated() {

		List<List<? extends Object>> data = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			List<Integer> lists = new ArrayList<>();
			for (int j = 0; j < 5; j++) {
				lists.add(j);
			}
			data.add((List<Integer>) lists);
		}

		Block block = new Block(data);
		Assert.assertEquals(block.size(), 5);
	}

	
}
