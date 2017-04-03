package org.bettelle.neon.is.qa.junit;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.bettelle.neon.is.qa.runner.Enclosed;
import org.bettelle.neon.is.qa.utilities.DriverFactory;
import org.bettelle.neon.is.qa.utilities.JunitHelper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Suite.SuiteClasses;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;

@RunWith(Enclosed.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SuiteClasses({constraintsMainStructure.N_BeforePart.class, constraintsMainStructure.NotParameterizedPart.class,
				constraintsMainStructure.TheParameterizedPart.class, constraintsMainStructure.Z_AfterPart.class})
public class constraintsMainStructure {

	/** All tests are placed in inner static classes to group them for convenience. The nested classes can be run together **/
	/** or individually. The @RunWith annotation can instantiate all the inner static classes by calling the enclosed.class **/

	public static WebDriver driver;
	protected static final int TIMEOUT = 30;
	static String newLine = System.getProperty("line.separator");
	private static Logger LOGGER = Logger.getLogger("InfoLogging");
	static DriverFactory.BrowserType type = DriverFactory.BrowserType.CHROME;



	/** ************ Static class that run before other static classes. ************* **/
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class N_BeforePart {

		public static void logging() {
			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
			for (LogEntry entry : logEntries) {
				System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
			}
		}

		@Rule	
		public TestWatcher testWatcher = new JunitHelper(LOGGER);

		@BeforeClass
		public static void setUpBeforeClass() throws InterruptedException {
			System.out.println("@BeforeClass Method: Initializing " + type + " Driver");
			RestAssured.baseURI = "http://Localhost:9090/neon-ods/api";
			//RestAssured.baseURI = "http://den-devdocker-1/cdsWebApp";
			driver = DriverFactory.getDriver(type);
			System.out.println(newLine + newLine);
		}

		@Test
		public void A1_DashboardNavigation() throws InterruptedException {	
			driver.get("http://localhost:8081/#/");
			driver.manage().window().maximize();
			WebElement OverviewTab = driver.findElement(By.id("sel1"));
			for (int i = 0; i < 2; i++){
				OverviewTab.click();
			}
			System.out.println("Neon Web Application Title ============= " + driver.getTitle());
			logging();
		}
		@Test
		public void A2_DashboardNavigation() throws Exception {
			new Select(driver.findElement(By.id("sel1"))).selectByVisibleText("DEV");
			logging();
		}

		@Test
		public void A3_DashboardNavigation() throws Exception {
			By constraintstab = By.xpath("//*[@id='app']/div/div/ul/li[7]/a");
			driver.findElement(constraintstab).click();
			logging();
		}
	}




	/** ******************* Single test methods, run only once. ***************** **/
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class NotParameterizedPart {

		public static void logging() {
			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
			for (LogEntry entry : logEntries) {
				System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
			}
		}

		@Rule	
		public TestWatcher testWatcher = new JunitHelper(LOGGER);

		@Test
		public void B1_DashboardNavigation() throws Exception {		
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By dropdown1 = By.id("siteSelect");
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdown1));
			for (int i = 0; i < 2; i++){
				driver.findElement(dropdown1).click();
			}
			logging();
		}

		@Test
		public void B2_DashboardNavigation() throws Exception {
			new Select(driver.findElement(By.id("siteSelect"))).selectByVisibleText("ABBY");
			logging();
		}

		@Test
		public void B3_DashboardNavigation() throws Exception {				

			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By relativePosition = By.xpath("//*[@id='app']/div/div/div/div/div/div/div[2]/div[2]/ul/li/div/div[2]/div");
			wait.until(ExpectedConditions.textToBePresentInElementLocated(relativePosition, "ABBY"));
			String rpText = driver.findElement(relativePosition).getText();
			System.out.println("Printing " + rpText);
			assertEquals("ABBY", rpText);
			driver.findElement(relativePosition).click();
			logging();
		}

		@Test
		public void B4_DashboardNavigation() throws Exception {				

			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By locationLabel = By.id("location");
			wait.until(ExpectedConditions.textToBePresentInElementLocated(locationLabel, "ABBY"));
			String locationText = driver.findElement(locationLabel).getText();
			System.out.println("Location Label " + locationText);
			assertEquals("ABBY", locationText);
			logging();
		}
		
		@Test
		public void B5_DashboardNavigation() throws Exception {
			new Select(driver.findElement(By.id("rev"))).selectByVisibleText("Revision 2");
			new Select(driver.findElement(By.id("rev"))).selectByVisibleText("Revision 3");
			new Select(driver.findElement(By.id("rev"))).selectByVisibleText("Revision 4");
			logging();
		}
		
		@Test
		public void B6_DashboardNavigation() throws Exception {
			driver.findElement(By.id("neon")).click();
			logging();
		}
		
		@Test
		public void B7_DashboardNavigation() throws Exception {				

			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By locationLabel = By.id("location");
			wait.until(ExpectedConditions.textToBePresentInElementLocated(locationLabel, "NEON"));
			String locationText = driver.findElement(locationLabel).getText();
			System.out.println("Location Label " + locationText);
			assertEquals("NEON", locationText);
			logging();
		}
	}



	/** *************** Loop: Tests that run for each set of parameters. ************ **/
	@RunWith(Parameterized.class)
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class TheParameterizedPart {

		//Fields
		private String iterator;
		private String site_id;
		private String site_value;

		public static void logging() {
			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
			for (LogEntry entry : logEntries) {
				System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
			}
		}

		@Rule	
		public TestWatcher testWatcher = new JunitHelper(LOGGER);


		@Parameters (name = "{index}: [{1}]={2}")
		public static Collection<Object[]> data() {
			JsonPath jsonPath = new JsonPath(new File("src/test/resources/data/location.site.info.data.json"));
			List<String> jsonObject1 = jsonPath.get("id");
			List<String> jsonObject2 = jsonPath.get("value");


			Object[][] data = new Object[jsonObject1.size()][];
			for (int i = 0; i < jsonObject1.size(); i++) {
				data[i] = new Object[] {new Integer(i).toString(),
						jsonObject1.get(i), jsonObject2.get(i),
				};
			}
			return Arrays.asList(data);
		}

		//Constructor
		public TheParameterizedPart(String iterator, String site_id, String site_value) {
			super();
			this.iterator = iterator;
			this.site_id = site_id;
			this.site_value = site_value;
		}

		@Test
		public void C1_DashboardNavigation() throws Exception {
			Integer i = new Integer(iterator).intValue();	
			System.out.println((i + 1) + ") Selecting site from data; " + "ID: " + site_id + ", NAME: " + site_value);
			new Select(driver.findElement(By.id("siteSelect"))).selectByVisibleText(site_value);
			logging();
		}

		@Test
		public void C2_DashboardNavigation() throws Exception {
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			new Select(driver.findElement(By.id("siteSelect"))).selectByVisibleText(site_value);
			By relativePosition = By.xpath("//*[@id='app']/div/div/div/div/div/div/div[2]/div[2]/ul/li/div/div[2]/div");
			wait.until(ExpectedConditions.textToBePresentInElementLocated(relativePosition, site_value));
			assertEquals(site_value, driver.findElement(relativePosition).getText());
			driver.findElement(relativePosition).click();
			logging();
		}

		@Test
		public void C3_DashboardNavigation() throws Exception {
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By locationLabel = By.id("location");
			wait.until(ExpectedConditions.textToBePresentInElementLocated(locationLabel, site_value));
			String locationText = driver.findElement(locationLabel).getText();
			assertEquals(site_value, locationText);
			logging();
		}
	}



	/** ************ Static class that run after other static classes. ************ **/
	public static class Z_AfterPart {
		public static void logging() {
			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
			for (LogEntry entry : logEntries) {
				System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
			}
		}

		@Rule	
		public TestWatcher testWatcher = new JunitHelper(LOGGER);

		@Test
		public void D1_DashboardNavigation() throws InterruptedException {	
			driver.get("http://localhost:8081/#/");
			driver.manage().window().maximize();
			System.out.println("Neon Web Application Title ============= " + driver.getTitle());
			logging();
		}

		@AfterClass
		public static void tearDownAfterClass() {
			LOGGER.info("Closing chromeDriver. Page Title: " + driver.getTitle()); 
			if (driver != null) {
				driver.quit();
			}
			System.out.println("@AfterClass Method: " + type + " Driver Closed");
			System.out.println(newLine + newLine);
		}
	}
}
