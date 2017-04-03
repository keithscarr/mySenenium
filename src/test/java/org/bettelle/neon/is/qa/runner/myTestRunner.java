package org.bettelle.neon.is.qa.runner;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.bettelle.neon.is.qa.junit.constraintsTab;
import org.bettelle.neon.is.qa.utilities.DriverFactory;
import org.bettelle.neon.is.qa.utilities.JunitHelper;
import org.bettelle.neon.is.qa.utilities.MyRunner;
import org.junit.runner.notification.Failure;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import com.jayway.restassured.path.json.JsonPath;

import org.junit.runners.Parameterized;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

@RunWith(MyRunner.class)
public class myTestRunner {
	
	public static WebDriver driver;
	protected static final int TIMEOUT = 30;
	static String newLine = System.getProperty("line.separator");
	private static Logger LOGGER = Logger.getLogger("InfoLogging");
	static DriverFactory.BrowserType type = DriverFactory.BrowserType.CHROME;
	
	public static void logging() {
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logEntries) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
		}
	}
	
	@Rule	
	public TestWatcher testWatcher = new JunitHelper(LOGGER);
	
	@Test
    public void subRunner() throws Exception {
        System.out.println("MainTest subRunner()");
        //JUnitCore.runClasses(SubTestWithRunner.class);
    }
	
	/** Tests that run for each set of parameters. */
    @RunWith(Parameterized.class)
    public static class ParameterizedTests2 {

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
		public ParameterizedTests2(String iterator, String test_id, String test_name) {
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
    
//    @RunWith(Parameterized.class)
//    public static class SubTestWithRunner {
//    	
//    	//Fields
//    	private String iterator;
//    	private String test_id;
//    	private String test_name;
//    	
////        @BeforeClass
////        public static void init() throws Exception {
////            System.out.println("SubTestWithRunner init()");
////        }
//        
//        @Parameters (name = "{index}: {0} {1}")
//    	public static Collection<Object[]> data() {
//    		
//    		JsonPath jsonPath = new JsonPath(new File("src/test/resources/data/location.site.info.data.json"));
//    		List<String> jsonObject1 = jsonPath.get("id");
//    		List<String> jsonObject2 = jsonPath.get("value");
//
//    		
//    		Object[][] data = new Object[jsonObject1.size()][];
//    		for (int i = 0; i < jsonObject1.size(); i++) {
//    			data[i] = new Object[] {new Integer(i).toString(),
//    					jsonObject1.get(i),
//    					jsonObject2.get(i),
//    			};
//    		}
//    		return Arrays.asList(data);
//    	}
//
//    	//Constructor
//    	public SubTestWithRunner(String iterator, String test_id, String test_name) {
//    		super();
//    		this.iterator = iterator;
//    		this.test_id = test_id;
//    		this.test_name = test_name;
//    	}
//
//    	@Test
//    	public void A7_DashboardNavigation() throws Exception {				
//    		
//    		System.out.println("Ivan");
//    		Integer i = new Integer(iterator).intValue();	
//    		System.out.println((i + 1) + ") Selecting site from data; " + "ID: " + test_id + ", NAME: " + test_name);
//    		//new Select(driver.findElement(By.id("siteSelect"))).selectByVisibleText(test_name);
//    	}
//    	
//    	@Test
//    	public void A8_DashboardNavigation() throws Exception {
//    		
//    		//Integer i = new Integer(iterator).intValue();	
//    		System.out.println("Lobo");
//    		//System.out.println((i + 1) + ") Selecting site from data; " + "ID: " + test_id + ", NAME: " + test_name);
////    		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
////    		new Select(driver.findElement(By.id("siteSelect"))).selectByVisibleText(test_name);
////    		By relativePosition = By.xpath("//*[@id='app']/div/div/div/div/div/div/div[2]/div[2]/ul/li/div/div[2]/div");
////    		wait.until(ExpectedConditions.textToBePresentInElementLocated(relativePosition, test_name));
////    		assertEquals(test_name, driver.findElement(relativePosition).getText());
////    		driver.findElement(relativePosition).click();
////    		
////    		By locationLabel = By.id("location");
////    		wait.until(ExpectedConditions.textToBePresentInElementLocated(locationLabel, test_name));
////    		String locationText = driver.findElement(locationLabel).getText();
////    		assertEquals(test_name, locationText);
//    		
//    	}
//    }
}
