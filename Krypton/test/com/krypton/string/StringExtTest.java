package com.krypton.string;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringExtTest {

	@Test
	public void testRotateAndPrint() {
		StringExt s = new StringExt("hello");
		s.rotateAndPrint();
	}

}
