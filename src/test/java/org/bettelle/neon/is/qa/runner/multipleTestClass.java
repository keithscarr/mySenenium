package org.bettelle.neon.is.qa.runner;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class multipleTestClass {
	
	private String data;
	private String expectedResult;
	static int testNum = 1;

	static Object [][] testdata = new Object[][] {
		{0},{1},{2},
	};
	static Object [][][] datavalues = new Object[][][]
			{
		{
			{ "A1","A1"},
			{ "B1","B1"},
			{ "C1","C1"}
		},
		{
			{ "A2","A2"},
			{ "B2","B2"},
			{ "C2","C2"},
		},
		{
			{ "A3","A3"},
			{ "B3","B3"},
			{ "C3","C3"},
		},
		{
			{ "A4","A4"},
			{ "B4","B4"},
			{ "C4","C4"},
		}
			};



			public multipleTestClass(int index) {
				this.data = (String) datavalues[testNum-1][index][0];
				this.expectedResult = (String) datavalues[testNum-1][index][1];
			}

			@Parameters
			public static Collection<Object[]> generateData() {
				return Arrays.asList (testdata);
			}

			@After public void nextTest () {
				if (++testNum > datavalues.length)
					testNum = 1;
				return;
			}

			@Test
			public void testOne()
			{
				String actualResult = this.data;
				System.out.println("T" + testNum + ":data = " + actualResult);
				assertEquals(actualResult, this.expectedResult);
			}

			@Test
			public void testTwo()
			{
				String actualResult = this.data;
				System.out.println("T" + testNum +":data = " + actualResult);
				assertEquals(actualResult, this.expectedResult);
			}

			@Test
			public void testThree()
			{
				String actualResult = this.data;
				System.out.println("T" + testNum +":data = " + actualResult);
				assertEquals(actualResult, this.expectedResult);
			}

			@Test
			public void testFour()
			{
				String actualResult = this.data;
				System.out.println("T" + testNum +":data = " + actualResult);
				assertEquals(actualResult, this.expectedResult);
			}

}
