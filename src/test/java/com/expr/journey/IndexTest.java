package com.expr.journey;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

public class IndexTest {

	@Test
	public void init() {
		Collection<String> columns = Arrays.asList("Name", "Age", "Address");
		Index index = new Index(columns);
		Assert.assertTrue(index.size() == 3);
	}
}
