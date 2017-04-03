package org.bettelle.neon.is.qa.testng;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class combineDataProvider {
	
//	@DataProvider
//    public Object[][] dp() {
//        return new Object[][]{{"TestNG"}, {"DataProvider"}};
//    }
//	
//	@Test(dataProvider="dp")
//    public void singleString(String a) {
//		
//		System.out.println("ivan");
//        System.out.println(a);
//    }
//	
//	@Test(dataProvider="dp")
//    public void superSingleString(String a) {
//    	
//    	System.out.println("ivan-enrique-lobo-padilla");
//        System.out.println(a);
//    }
        
//    @Test(dataProvider="multi-param")
//    public void multiParameter(String a, int i, long l, boolean b) {
//    	
//    	System.out.println("enrique");
//        System.out.println("String: " + a + ", int: " + i + ", long: " + l + ", boolean: " + b);
//    }
//    
//    @DataProvider(name="multi-param")
//    public Object[][] dpMultiParam() {
//        return new Object[][]{
//                {"a", 1, 10L, true},
//                {"b", 2, 20L, false},
//                {"c", 3, 30L, true},
//                {"d", 4, 40L, false}};
//    }

	@DataProvider
	public static Object[][] dpWithMethod(Method m) {
		switch (m.getName()) {
		case "client1":
			return new Object[][]{{"one"}, {"two"}};
		case "client2":
			return new Object[][]{{"1"}, {"2"}};
		default:
			return new Object[][]{{"i"}, {"ii"}};
		}
	}
	
	@Test
	public class embedded {

		@Test(dataProvider="dpWithMethod")
		public void client1(String p) {

			System.out.println("padilla");
			System.out.println(p);
		}

		@Test(dataProvider="dpWithMethod")
		public void client2(String p) {

			System.out.println("ivan-lobo");
			System.out.println(p);
		}

		@Test(dataProvider="dpWithMethod")
		public void commonClient(String p) {

			System.out.println("enrique-padilla");
			System.out.println(p);
		}
	}
	
//	@DataProvider
//    public Object[][] inheritedDp() {
//        return new Object[][]{{"one"}, {"two"}};
//    }
//	
//	@Test(dataProvider="inheritedDp")
//    public void numericalInWords(String numerical) {
//    	
//    	System.out.println("lobo");
//        System.out.println(numerical);
//    }
		
//	 	@DataProvider
//	    public Object[][] inheritedDp() {
//	        return new Object[][]{{"one"}, {"two"}};
//	    }
//	     
//	    @Test(dataProvider="dp")
//	    public void superSingleString(String a) {
//	    	
//	    	System.out.println("ivan");
//	        System.out.println(a);
//	    }
//
//	    @Test(dataProvider="dp")
//	    public void singleString(String a) {
//	    	
//	    	System.out.println("lobo");
//	        System.out.println(a);
//	    }
//	     
//	    @DataProvider
//	    public Object[][] dp() {
//	        return new Object[][]{{"TestNG"}, {"DataProvider"}};
//	    }
//	         
//	    @Test(dataProvider="multi-param")
//	    public void multiParameter(String a, int i, long l, boolean b) {
//	    	
//	    	System.out.println("padilla");
//	        System.out.println("String: " + a + ", int: " + i + ", long: " + l + ", boolean: " + b);
//	    }
//	     
//	    @DataProvider(name="multi-param")
//	    public Object[][] dpMultiParam() {
//	        return new Object[][]{
//	                {"a", 1, 10L, true},
//	                {"b", 2, 20L, false},
//	                {"c", 3, 30L, true},
//	                {"d", 4, 40L, false}};
//	    }
//	     
//	    @Test(dataProvider="inheritedDp")
//	    public void numericalInWords(String numerical) {
//	    	
//	    	System.out.println("enrique");
//	        System.out.println(numerical);
//	    }

	
//	@Test(dataProvider="dp")
//    public void singleString(String a) {
//		
//		System.out.println("ivan");
//        System.out.println(a);
//    }
//     
//    @DataProvider
//    public Object[][] dp() {
//        return new Object[][]{{"TestNG"}, {"DataProvider"}};
//    }
//         
//    @Test(dataProvider="multi-param")
//    public void multiParameter(String a, int i, long l, boolean b) {
//    	
//    	System.out.println("Lobo");
//        System.out.println("String: " + a + ", int: " + i + ", long: " + l + ", boolean: " + b);
//    }
//     
//    @DataProvider(name="multi-param")
//    public Object[][] dpMultiParam() {
//        return new Object[][]{
//                {"a", 1, 10L, true},
//                {"b", 2, 20L, false},
//                {"c", 3, 30L, true},
//                {"d", 4, 40L, false}};
//    }

}
