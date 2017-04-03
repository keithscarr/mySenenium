package org.bettelle.neon.is.qa.runner;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.AssumptionViolatedException;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.ParentRunner;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerScheduler;

import com.jayway.restassured.path.json.JsonPath;


public class ParentRunnerTest {

	public static String log = "";
	static String newLine = System.getProperty("line.separator");

	private static class CountingRunListener extends RunListener {
		private int testStarted = 0;
		private int testFinished = 0;
		private int testFailure = 0;
		private int testAssumptionFailure = 0;
		private int testIgnored = 0;

		@Override
		public void testStarted(Description description) throws Exception {
			testStarted++;
			System.out.println("STARTING: " + description.getMethodName());
		}

		@Override
		public void testFinished(Description description) throws Exception {
			testFinished++;
			System.out.println("FINISHED: " + description.getMethodName());
			System.out.println(newLine + newLine);
		}

		@Override
		public void testFailure(Failure failure) throws Exception {
			testFailure++;
			System.out.println("Failed: " + failure.getDescription().getMethodName());
			System.out.println(failure.getTrace());
		}

		@Override
		public void testAssumptionFailure(Failure failure) {
			testAssumptionFailure++;
		}

		@Override
		public void testIgnored(Description description) throws Exception {
			testIgnored++;
			System.out.println("Ignored: " + description.getMethodName());
		}
	}

	private CountingRunListener runTestWithParentRunner(Class<?> testClass) throws InitializationError {
		CountingRunListener listener = new CountingRunListener();
		RunNotifier runNotifier = new RunNotifier();
		runNotifier.addListener(listener);
		ParentRunner runner = new BlockJUnit4ClassRunner(testClass);
		runner.run(runNotifier);
		return listener;
	}
	
	@RunWith(Parameterized.class)
    public static class TheParameterizedPart {

		//Fields
		private String iterator;
		private String test_id;
		private String test_name;

		@Parameters (name = "{index}: {0} {1}")
		public static Collection<Object[]> data() {

			JsonPath jsonPath = new JsonPath(new File("src/test/resources/data/location.site.info.data.json"));
			List<String> jsonObject1 = jsonPath.get("id");
			List<String> jsonObject2 = jsonPath.get("value");


			Object[][] data = new Object[jsonObject1.size()][];
			for (int i = 0; i < jsonObject1.size(); i++) {
				data[i] = new Object[] {new Integer(i).toString(),
						jsonObject1.get(i),
						jsonObject2.get(i),
				};
			}
			return Arrays.asList(data);
		}

		//Constructor
		public TheParameterizedPart(String iterator, String test_id, String test_name) {
			super();
			this.iterator = iterator;
			this.test_id = test_id;
			this.test_name = test_name;
		}

		@Test
		public void A7_DashboardNavigation() throws Exception {				

			System.out.println("Ivan");
			Integer i = new Integer(iterator).intValue();	
			System.out.println((i + 1) + ") Selecting site from data; " + "ID: " + test_id + ", NAME: " + test_name);
			//new Select(driver.findElement(By.id("siteSelect"))).selectByVisibleText(test_name);
		}

		@Test
		public void A8_DashboardNavigation() throws Exception {

			//Integer i = new Integer(iterator).intValue();	
			System.out.println("Lobo");
			//System.out.println((i + 1) + ") Selecting site from data; " + "ID: " + test_id + ", NAME: " + test_name);
			//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			//		new Select(driver.findElement(By.id("siteSelect"))).selectByVisibleText(test_name);
			//		By relativePosition = By.xpath("//*[@id='app']/div/div/div/div/div/div/div[2]/div[2]/ul/li/div/div[2]/div");
			//		wait.until(ExpectedConditions.textToBePresentInElementLocated(relativePosition, test_name));
			//		assertEquals(test_name, driver.findElement(relativePosition).getText());
			//		driver.findElement(relativePosition).click();
			//		
			//		By locationLabel = By.id("location");
			//		wait.until(ExpectedConditions.textToBePresentInElementLocated(locationLabel, test_name));
			//		String locationText = driver.findElement(locationLabel).getText();
			//		assertEquals(test_name, locationText);

		}
    }

	@Test
	public void parentRunnerTestMethods1() throws InitializationError {

		CountingRunListener countingRunListener = runTestWithParentRunner(TestTest.class);
		System.out.println("==============================================");
		System.out.println("Test Suite");
		System.out.println("Total tests started: " +  countingRunListener.testStarted);
		System.out.println("Total tests finished: " + countingRunListener.testFinished);
		System.out.println("Total tests passed: " + (countingRunListener.testStarted - countingRunListener.testFailure));
		System.out.println("Total tests failed: " +  countingRunListener.testFailure);
		System.out.println("Total asserts failed: " +  countingRunListener.testAssumptionFailure);
		System.out.println("Total tests ignored: " + countingRunListener.testIgnored);
		System.out.println("==============================================");
	}
	
	@Test
	public void parentRunnerTestMethods2() throws InitializationError {

		CountingRunListener countingRunListener = runTestWithParentRunner(TheParameterizedPart.class);
		System.out.println("==============================================");
		System.out.println("Test Suite");
		System.out.println("Total tests started: " +  countingRunListener.testStarted);
		System.out.println("Total tests finished: " + countingRunListener.testFinished);
		System.out.println("Total tests passed: " + (countingRunListener.testStarted - countingRunListener.testFailure));
		System.out.println("Total tests failed: " +  countingRunListener.testFailure);
		System.out.println("Total asserts failed: " +  countingRunListener.testAssumptionFailure);
		System.out.println("Total tests ignored: " + countingRunListener.testIgnored);
		System.out.println("==============================================");
	}

	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class TestTest {

		@Test
		public void A1_testListener(){

			System.out.println("ivan1");
		}

		@Test
		public void A2_testFalseAssertion(){

			System.out.println("ivan2");
		}

		//		@Test
		//		public void A3_testIgnore(){
		//
		//			System.out.println("ivan3");
		//			assertEquals("ivan", "Lobo");
		//		}

		//		@Test
		//		public void A4_testException(){
		//
		//			System.out.println("ivan4");
		//			fail("Demo for failed test");
		//		}

		@Test
		public void A5_testListener1(){

			System.out.println("ivan5");
		}

		@Test
		public void A6_testListener2(){

			System.out.println("ivan6");
		}

		@Test
		public void A7_testListener3(){

			System.out.println("ivan7");
		}
	}

}
