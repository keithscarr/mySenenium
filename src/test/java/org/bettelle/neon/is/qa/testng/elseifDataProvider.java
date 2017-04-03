package org.bettelle.neon.is.qa.testng;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class elseifDataProvider {
	
	@Test(dataProvider = "dataProvider", groups = {"groupA"})
	public void test1(int number) {
		
		System.out.println("Ivan");
		//Assert.assertEquals(number, 1);
	}

	@Test(dataProvider = "dataProvider", groups = {"groupB"})
	public void test2(int number) {
		
		System.out.println("Lobo");
		//Assert.assertEquals(number, 2);
	}

	@DataProvider(name = "dataProvider")
	public Object[][] provideData(ITestContext context) {

		Object[][] result = null;

		//get test name
		System.out.println(context.getName());

		for (String group : context.getIncludedGroups()) {

			System.out.println("group : " + group);

			if ("groupA".equals(group)) {
				result = new Object[][] { { 1 } };
				break;
			}

		}

		if (result == null) {
			result = new Object[][] { { 2 } };
		}
		return result;
	}

	
//	@Test(dataProvider = "dataProvider")
//	public void test1(int number, int expected) {
//		
//		System.out.println("Ivan");
//		Assert.assertEquals(number, expected);
//	}
//
//	@Test(dataProvider = "dataProvider")
//	public void test2(String email, String expected) {
//		
//		System.out.println("Lobo");
//		Assert.assertEquals(email, expected);
//	}
//
//	@DataProvider(name = "dataProvider")
//	public Object[][] provideData(Method method) {
//
//		Object[][] result = null;
//
//		if (method.getName().equals("test1")) {
//			result = new Object[][] {
//				{ 1, 1 }, { 200, 200 }
//			};
//		} else if (method.getName().equals("test2")) {
//			result = new Object[][] {
//				{ "test@gmail.com", "test@gmail.com" },
//				{ "test@yahoo.com", "test@yahoo.com" }
//			};
//		}
//		return result;
//	}
}
