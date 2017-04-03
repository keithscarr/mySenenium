package org.bettelle.neon.is.qa.testng;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class nestedClass {

	@Test
	public void testFoo1() {
		int expected = 3;
		assertEquals(1 + 2, expected);
		System.out.println("testFoo1");
	}

	@Test
	public void testFoo2() {
		int expected = 3;
		assertEquals(1 + 2, expected);
		System.out.println("testFoo2");
	}

	@Test
	public class TestBar {

		@Test
		public void testBar1() {
			int expected = 4;
			assertEquals(2 + 2, expected);
			System.out.println("testBar1");
		}

		@Test
		public void testBar2() {
			int expected = 4;
			assertEquals(2 + 2, expected);
			System.out.println("testBar2");
		}
		
		@Test
		public class TestBas {

			@Test
			public void testBar1() {
				int expected = 4;
				assertEquals(2 + 2, expected);
				System.out.println("testBar1");
			}

			@Test
			public void testBar2() {
				int expected = 4;
				assertEquals(2 + 2, expected);
				System.out.println("testBar2");
			}
		}
	}
}
