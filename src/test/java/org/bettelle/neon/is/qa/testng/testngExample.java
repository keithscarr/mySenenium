package org.bettelle.neon.is.qa.testng;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class testngExample {
	
	@Test(dataProvider="scenarioData")
	public void scenario1(String scenarioData) {
		
		System.out.println("Ivan");
		System.out.println("Scenario testing: Data(" + scenarioData + ")");
	}
	
	@Test(dataProvider="scenarioData")
	public void scenario2(String scenarioData) {
		
		System.out.println("Lobo");
		System.out.println("Scenario testing: Data(" + scenarioData + ")");
	}
	
	@Test(dataProvider="scenarioData")
	public void commonScenarios(String scenarioData) {
		
		System.out.println("Padilla");
		System.out.println("Common Scenarios testing: Data(" + scenarioData + ")");
	}

	
	@Test(dataProvider="TestType")
	public void integrationTest(String data) {
		
		System.out.println("Enrique");
		System.out.println("Integration testing: Data(" + data + ")");
	}

	@DataProvider(name="client1")
	public static Object[][] getClient1Data() {
		return new Object[][]{{1}};		
	}
	
	@DataProvider(name="client2")
	public static Object[][] getClient2Data() {
		return new Object[][]{{2}};		
	}
	
	@DataProvider(name="scenarioData")
	public static Object[][] getScenarioData(Method method) {		
		String testCase = method.getName();
		if ("scenario1".equals(testCase)) {
			return new Object[][]{{"one"}, {"two"}};
		} else if ("scenario2".equals(testCase)) {
			return new Object[][]{{"1"}, {"2"}};
		} else {
			return new Object[][]{{"i"}, {"ii"}};
		}
	}	
	
	@DataProvider(name="TestType")
	public static Object[][] getTestTypeData(ITestContext context) {		
		String testName = context.getName();
		if ("IntegrationLevel".equals(testName)) {
			return new Object[][]{{"Integration test data"}};
		} else if ("AcceptanceLevel".equals(testName)) {
			return new Object[][]{{"Acceptance test data"}};
		} else {
			return new Object[][]{{"Common test data"}};
		}
	}	

}
