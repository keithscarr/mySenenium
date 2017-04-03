package org.bettelle.neon.is.qa.junit;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import org.bettelle.neon.is.qa.runner.Enclosed;
import org.bettelle.neon.is.qa.utilities.DriverFactory;
import org.bettelle.neon.is.qa.utilities.JunitHelper;
import org.bettelle.neon.is.qa.utilities.jsonFormater;
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
import com.jayway.restassured.response.Response;

@RunWith(Enclosed.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SuiteClasses({ metadataMainStructure.N_BeforePart.class, metadataMainStructure.O_NotParameterizedPart.class,
	//metadataMainStructure.Q_domainsAPI.class, metadataMainStructure.R_updateMetadata.class,
	metadataMainStructure.Z_AfterPart.class, metadataMainStructure.P_LocationMetadata.class})
public class metadataMainStructure {

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
			By constraintstab = By.xpath("//*[@id='app']/div/div/ul/li[11]/a");
			driver.findElement(constraintstab).click();
			logging();
		}
	}



	/** ******************* Single test methods, run only once. ***************** **/
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class O_NotParameterizedPart {

		public static void logging() {
			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
			for (LogEntry entry : logEntries) {
				System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
			}
		}

		@Rule	
		public TestWatcher testWatcher = new JunitHelper(LOGGER);

		@Test
		public void B2_DashboardNavigation() throws Exception {		
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By dropdown1 = By.id("siteSelect");
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdown1));
			for (int i = 0; i < 2; i++){
				driver.findElement(dropdown1).click();
			}
			logging();
		}


		@Test
		public void B3_DashboardNavigation() throws Exception {
			new Select(driver.findElement(By.id("siteSelect"))).selectByVisibleText("CPER");
			logging();
		}

		@Test
		public void B4_DashboardNavigation() throws Exception {
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By locationLabel = By.id("site");
			wait.until(ExpectedConditions.textToBePresentInElementLocated(locationLabel, "CPER"));
			assertEquals("CPER", driver.findElement(locationLabel).getText());
			logging();
		}

		@Test
		public void B5_DshboardNavigation() throws Exception {
			By domainLabel = By.id("domain");
			assertEquals("D10", driver.findElement(domainLabel).getText());
			logging();
		}

		@Test
		public void B6_DashboardNavigation() throws Exception {
			By idLabel = By.id("name");
			assertEquals("CPER", driver.findElement(idLabel).getText());
			logging();
		}

		@Test
		public void B7_DashboardNavigation() throws Exception {
			By clearbutton = By.id("clearButton");
			driver.findElement(clearbutton).click();
			logging();
		}

		@Test
		public void B8_DashboardNavigation() throws Exception {
			System.out.println("Selecting Time-Zone: " + "US/Alaska" + ", LvlMeasTow: 0, DistZaxsLvlMeasTow: 360");
			new Select(driver.findElement(By.id("zoneTime"))).selectByVisibleText("US/Alaska");
			logging();
		}

		@Test
		public void B9_DashboardNavigation() throws Exception {
			driver.findElement(By.id("lvlMeasTow")).sendKeys(String.valueOf(0));
			driver.findElement(By.id("distZaxsLvlMeasTow")).sendKeys(String.valueOf(360));
			logging();

		}

		@Test
		public void C1_DashboardNavigation() throws Exception {
			By savebutton = By.id("saveButton");
			driver.findElement(savebutton).click();
			//Thread.sleep(6000);
			logging();
		}

		@Test
		public void C2_DashboardNavigation() throws Exception {

			String homePageId = driver.getWindowHandle();
			Set<String> popUps = driver.getWindowHandles();
			Iterator<String> iterator = popUps.iterator();
			String currentPageId;
			while(iterator.hasNext()) {
				currentPageId = iterator.next().toString();
				System.out.println("Current Window: " + currentPageId);
				if(!currentPageId.equals(homePageId)) {
					driver.switchTo().window(currentPageId);
					assertEquals("Location metadata updated successfully!", 
							driver.findElement(By.id("notification-wrapper")).getText());
				}
			}
			logging();
		}

		@Test
		public void C3_DashboardNavigation() throws Exception {
			String homePageId = driver.getWindowHandle();
			Set<String> popUps = driver.getWindowHandles();
			Iterator<String> iterator = popUps.iterator();
			String currentPageId;
			while(iterator.hasNext()) {
				currentPageId = iterator.next().toString();
				System.out.println("Current Window: " + currentPageId);
				if(!currentPageId.equals(homePageId)) {
					driver.switchTo().window(currentPageId);
					driver.close();
				}
			}
			logging();
		}

		@Test 
		public void C4_locationsID () throws Exception {

			LOGGER.info("REQUEST");
			Response response = RestAssured
					.given()
					.header("Content-Type", "application/json")
					.log().all()
					.when()
					.get("/locationmetadata/74");
			LOGGER.info("RESPONSE");
			response.then()
			.log().all()
			.statusCode(200);
			String responseString = response.asString();
			FileWriter file = new FileWriter("src/test/resources/data/location.metadata.json");
			file.write(responseString.toString());file.flush();file.close();
		}
	}



	/** *************** Loop: Validate Metadata. ************ **/
	@RunWith(Parameterized.class)
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class P_LocationMetadata {
		//Fields
		private String iterator;
		//private String location_id;
		private String name_value;
		private String meta_value;
		//private String location_metadata_id;
		//private String field_name;
		private String meta_description;

		public static void logging() {
			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
			for (LogEntry entry : logEntries) {
				System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
			}
		}

		@Rule	
		public TestWatcher testWatcher = new JunitHelper(LOGGER);

		@Parameters (name = "{index}: {0} {1} {2}")
		public static Collection<Object[]> data() {
			JsonPath jsonPath = new JsonPath(new File("src/test/resources/data/location.metadata.json"));
			List<String> name = jsonPath.get("name");
			List<String> metadata = jsonPath.get("metadata");
			List<String> description = jsonPath.get("description");

			Object[][] data = new Object[metadata.size()][];
			for (int i = 0; i < metadata.size(); i++) {
				data[i] = new Object[] {new Integer(i).toString(),
						metadata.get(i), metadata.get(i), description.get(i)
				};
			}
			return Arrays.asList(data);
		}

		//Constructor
		public P_LocationMetadata(String iterator, String nameValue, String metadataValue, String metaDescription) {
			super();
			this.iterator = iterator;
			this.name_value = nameValue;
			this.meta_value = metadataValue;
			this.meta_description = metaDescription;
		}

		@Test 
		public void D1_locationMetadata () throws Exception {

			String metaLocName = name_value;
			switch (metaLocName) {
			case "ZoneTime":
				By timezone = By.id("zoneTime");
				assertEquals(meta_value, driver.findElement(timezone).getAttribute("value"));
				logging();
				break;
			case "LvlMeasTow":
				By lvlTower = By.id("lvlMeasTow");
				assertEquals(meta_value, driver.findElement(lvlTower).getAttribute("value"));
				logging();
				break;
			case "DistZaxsLvlMeasTow":
				By distTower = By.id("distZaxsLvlMeasTow");
				assertEquals(meta_value, driver.findElement(distTower).getAttribute("value"));
				logging();
				break;
			}
		}
	}


	/** *************** Loop: Tests that run for each set of parameters. ************ **/
	@RunWith(Parameterized.class)
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class Q_domainsAPI {
		//Fields
		private String iterator;
		private String location_id;
		private String location_value;
		private String domain_value;

		public static void logging() {
			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
			for (LogEntry entry : logEntries) {
				System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
			}
		}

		@Rule	
		public TestWatcher testWatcher = new JunitHelper(LOGGER);

		@Parameters (name = "{index}: {0} {1}")
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
		public Q_domainsAPI(String iterator, String location_id, String location_value) {
			super();
			this.iterator = iterator;
			this.location_id = location_id;
			this.location_value = location_value;
		}

		@Test 
		public void E1_locationsID () throws Exception {

			LOGGER.info("REQUEST");
			Response response = RestAssured
					.given()
					.header("Content-Type", "application/json")
					.log().all()
					.when()
					.get("/locations/" + location_id.toString());
			LOGGER.info("RESPONSE");
			response.then()
			.log().all()
			.statusCode(200);
			String responseString = response.asString();
			domain_value = (String) jsonFormater.readJSON(responseString, "domain");
			System.out.println("Domain: " + domain_value);
		}

		@Test
		public void E2_DashboardNavigation() throws Exception {
			Integer i = new Integer(iterator).intValue();	
			System.out.println((i + 1) + ") Selecting site from data; " + "ID: " + location_id + ", VALUE: " + location_value);
			new Select(driver.findElement(By.id("siteSelect"))).selectByVisibleText(location_value);
			logging();
		}

		@Test
		public void E3_DashboardNavigation() throws Exception {
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By locationLabel = By.id("site");
			wait.until(ExpectedConditions.textToBePresentInElementLocated(locationLabel, location_value));
			assertEquals(location_value, driver.findElement(locationLabel).getText());
			logging();
		}

		@Test
		public void E4_DshboardNavigation() throws Exception {
			By domainLabel = By.id("domain");
			assertEquals(domain_value, driver.findElement(domainLabel).getAttribute("value"));
			logging();
		}

		@Test
		public void E5_DashboardNavigation() throws Exception {
			By idLabel = By.id("name");
			assertEquals(location_value, driver.findElement(idLabel).getText());
			logging();
		}
	}


	/** *************** Loop: Tests to update metadata ************ **/
	@RunWith(Parameterized.class)
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class R_updateMetadata {
		//Fields
		private String locationVAlue;
		private String timeZone;
		private int LvlMeasTow;
		private int DistZaxsLvlMeasTow;

		public static void logging() {
			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
			for (LogEntry entry : logEntries) {
				System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
			}
		}

		@Rule	
		public TestWatcher testWatcher = new JunitHelper(LOGGER);

		@Parameters(name = "{index}: {1} {2} {3}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][]{
				{"STEI", "US/Alaska", 0, 0}, {"TREE", "US/Aleutian", 1, 40},
				{"KONZ", "US/Arizona", 2, 60}, {"GRSM", "US/Central", 3, 80},
				{"LENO", "US/East-Indiana", 5, 100}, {"NIWO", "US/Eastern", 4, 130},
				{"SJER", "US/Hawaii", 5, 4}, {"UKFS", "US/Indiana-Starke", 6, 160}, 
				{"STER", "US/Michigan", 7, 190}, {"BASC", "US/Mountain", 8, 250},
				{"MAME", "US/Pacific", 9, 300}, {"KONA", "US/Pacific-New", 0, 350},
				{"CPER", "US/Samoa", 1, 360}
			});
		}

		//Constructor
		public R_updateMetadata(String locationvalue, String timezone, int lvlMeasTow, int distZaxsLvlMeasTow) {
			super();
			this.locationVAlue = locationvalue;
			this.timeZone = timezone;
			this.LvlMeasTow = lvlMeasTow;
			this.DistZaxsLvlMeasTow = distZaxsLvlMeasTow;
		}

		@Test
		public void F1_DashboardNavigation() throws Exception {
			new Select(driver.findElement(By.id("siteSelect"))).selectByVisibleText(locationVAlue);
			logging();
		}

		@Test
		public void F2_DashboardNavigation() throws Exception {
			System.out.println("Selecting Time-Zone: " + timeZone + ", LvlMeasTow: " + LvlMeasTow + ", DistZaxsLvlMeasTow: " + DistZaxsLvlMeasTow);
			new Select(driver.findElement(By.id("zoneTime"))).selectByVisibleText(timeZone);
			logging();
		}

		@Test
		public void F3_DashboardNavigation() throws Exception {
			driver.findElement(By.id("lvlMeasTow")).sendKeys(String.valueOf(LvlMeasTow));
			driver.findElement(By.id("distZaxsLvlMeasTow")).sendKeys(String.valueOf(DistZaxsLvlMeasTow));
			logging();
		}

		@Test
		public void F4_DashboardNavigation() throws Exception {
			By savebutton = By.id("saveButton");
			driver.findElement(savebutton).click();
			//Thread.sleep(6000);
			logging();
		}

		@Test
		public void F5_DashboardNavigation() throws Exception {
			String homePageId = driver.getWindowHandle();
			Set<String> popUps = driver.getWindowHandles();
			Iterator<String> iterator = popUps.iterator();
			String currentPageId;
			while(iterator.hasNext()) {
				currentPageId = iterator.next().toString();
				System.out.println("Current Window: " + currentPageId);
				if(!currentPageId.equals(homePageId)) {
					driver.switchTo().window(currentPageId);
					assertEquals("No metadata defined for Location " + locationVAlue + " (404)", 
							driver.findElement(By.id("notification-wrapper")).getText());
				}
			}
			logging();
		}

		@Test
		public void F6_DashboardNavigation() throws Exception {
			String homePageId = driver.getWindowHandle();
			Set<String> popUps = driver.getWindowHandles();
			Iterator<String> iterator = popUps.iterator();
			String currentPageId;
			while(iterator.hasNext()) {
				currentPageId = iterator.next().toString();
				System.out.println("Current Window: " + currentPageId);
				if(!currentPageId.equals(homePageId)) {
					driver.switchTo().window(currentPageId);
					driver.close();
				}
			}
			logging();
		}

		@Test
		public void F7_DashboardNavigation() throws Exception {
			By clearbutton = By.id("clearButton");
			driver.findElement(clearbutton).click();
			logging();
		}
	}



	/** ************ Static class that run after other static classes. ************ **/
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
		public void G1_DashboardNavigation() throws InterruptedException {	
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
