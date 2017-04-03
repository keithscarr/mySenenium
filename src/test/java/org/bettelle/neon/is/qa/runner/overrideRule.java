package org.bettelle.neon.is.qa.runner;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AssumptionViolatedException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.rules.MethodRule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import com.jayway.restassured.path.json.JsonPath;

import static org.junit.Assert.fail; 

@RunWith(Parameterized.class)
public class overrideRule  {

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
	public overrideRule(String iterator, String test_id, String test_name) {
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

	@Test
    public void shouldKneelWhenTheQueenPasses() throws Exception {
    	
    	System.out.println("3");
    }

//	@Test
//	public void testListener(){
//
//		System.out.println("ivan1");
//	}
//
//	@Test
//	public void testFalseAssertion(){
//
//		System.out.println("ivan2");
//	}
//
//	@Test
//	public void testIgnore(){
//
//		System.out.println("ivan3");
//		assertEquals("ivan", "Lobo");
//	}
//
//	@Test
//	public void testException(){
//
//		System.out.println("ivan4");
//		fail("Demo for failed test");
//	}
//
//	@Test
//	public void testListener1(){
//
//		System.out.println("ivan5");
//	}
//
//	@Test
//	public void testListener2(){
//
//		System.out.println("ivan6");
//	}
//
//	@Test
//	public void testListener3(){
//
//		System.out.println("ivan7");
//	}

	@Rule 
	public TestWatcher watcher = new TestWatcher() { 

		@Override
		protected void starting(Description description) {
			System.out.println("starting test case: " + description.getMethodName());
		};

		@Override
		protected void failed(Throwable e, org.junit.runner.Description description) {
			System.err.println("failed test case: " + description.getMethodName());
			System.err.println("response: " + e.fillInStackTrace());
		};

		@Override 
		protected void succeeded(Description description) { 
			System.out.println("Passed");
		}
		@Override
		protected void finished(Description description) {
			System.out.println("finished test case: " + description.getMethodName());
		};
	};

}
