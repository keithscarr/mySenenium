package org.bettelle.neon.is.qa.junit;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.bettelle.neon.is.qa.utilities.DriverFactory;
import org.bettelle.neon.is.qa.utilities.JunitHelper;
import org.bettelle.neon.is.qa.utilities.MyRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.test.annotation.Repeat;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;


@RunWith(Parameterized.class)
public class constraintsTab {
	
	public static WebDriver driver;
	protected static final int TIMEOUT = 30;
	static String newLine = System.getProperty("line.separator");
	private static Logger LOGGER = Logger.getLogger("InfoLogging");
	static DriverFactory.BrowserType type = DriverFactory.BrowserType.CHROME;
	//Fields
	private String iterator;
	private String test_id;
	private String test_name;

	public static void logging() {
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logEntries) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
		}
	}
	
	 //@Rule
	  //public Class<MyRunner> exception = MyRunner.class;



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
		System.out.println("Neon Web Application Title ============= " + driver.getTitle());
		logging();
	}

	@Test
	public void A2_DashboardNavigation() throws Exception {	
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		By constraintstab = By.xpath("//*[@id='app']/div/div/ul/li[7]/a");
		wait.until(ExpectedConditions.presenceOfElementLocated(constraintstab));
		driver.findElement(constraintstab).click();
		logging();
	}

	@Test
	public void A3_DashboardNavigation() throws Exception {		
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		By dropdown1 = By.id("siteSelect");
		wait.until(ExpectedConditions.presenceOfElementLocated(dropdown1));
		for (int i = 0; i < 2; i++){
			driver.findElement(dropdown1).click();
		}
		logging();
	}
	
	@Test
	public void A4_DashboardNavigation() throws Exception {				
		
		new Select(driver.findElement(By.id("siteSelect"))).selectByVisibleText("STEI");
	}
	
//	@Test
//	public void A5_DashboardNavigation() throws Exception {				
//		
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By relativePosition = By.xpath("//*[@id='app']/div/div/div/div/div/div/div[2]/div[2]/ul/li/div/div[2]/div");
//		wait.until(ExpectedConditions.textToBePresentInElementLocated(relativePosition, "STEI"));
//		String rpText = driver.findElement(relativePosition).getText();
//		System.out.println("Printing " + rpText);
//		assertEquals("STEI", rpText);
//	}
	
	
	@Test
	public void A4_siteInformation() throws Exception {

		LOGGER.info("REQUEST");
		Response response = RestAssured
				.given()
				.header("Accept", "application/json, text/plain, */*")
				.log().all()
				.when()
				.get("/locations/siteinfo");
		LOGGER.info("RESPONSE");
		response.then()
		.log().all()
		//.log().status() 
		//.log().headers()
		//.log().ifError()
		.statusCode(200);
		
		//String responseString = response.asString();
		//FileWriter file = new FileWriter("src/test/resources/data/location.site.info.data.json");
		//file.write(responseString.toString());file.flush();file.close();
	}
	
//	@Test
//	public void A6_key_Value_Pair() throws Exception {			
//		WebElement element = driver.findElement(By.id("siteSelect"));
//		Select  selecting1 = new Select(element);
//		WebElement site_element = driver.findElement(By.id("siteSelect"));
//		Select  selecting2 = new Select(site_element);
//		List <WebElement> elementcount = selecting2.getOptions();
//		for(int i = 1 ; i < selecting1.getOptions().size(); i++)
//		System.out.println(i + ") " + "\"" + selecting1.getOptions().get(i).getAttribute("value") +
//		"\": \"" + elementcount.get(i).getText() + "\"");	
//		logging();
//		Thread.sleep(2000);
//	}
	
	
	@Parameters (name = "{index}: [{1}]={2}") 
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
	public constraintsTab(String iterator, String test_id, String test_name) {
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
	
//	@Test
//	public void A7_DashboardNavigation() throws Exception {		
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By selecOne = By.xpath("//*[@id='siteSelect']/option[2]");
//		wait.until(ExpectedConditions.presenceOfElementLocated(selecOne));
//		driver.findElement(selecOne).click();
//		logging();
//		Thread.sleep(2000);
//	}
	
//	@Test
//	public void A7_DashboardNavigation() throws Exception {
//		List <WebElement> options = driver.findElements(By.id("siteSelect"));
//	    String element;
//	    for(int i=2; i<options.size(); i++)
//	    {
//	        element = options.get(i).getAttribute("value");
//	        //if(element.equals("BHX")){
//	            options.get(i).click();
//	        //}
//	    }
		
//		WebElement dropdown1 = driver.findElement(By.id("siteSelect"));
//		Select  selecting = new Select(dropdown1);
//		for(int i=2 ; i<3; i++) {
//			String value = selecting.getOptions().get(i).getAttribute("value");
//			System.out.println(value);		
//			By selecOne = By.xpath("//*[@id='siteSelect']/option[" + value + "]");
//			driver.findElement(selecOne).click();
			
//			By selecteditem = By.id("siteSelect");
//			wait.until(ExpectedConditions.presenceOfElementLocated(selecteditem));
//			driver.findElement(selecteditem).click();
//		}
		
//		{
//			String value = selecting.getOptions().get(i).getAttribute("value");
//			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//			By selecOne = By.xpath("//*[@id='siteSelect']/option[" + value + "]");
//			wait.until(ExpectedConditions.presenceOfElementLocated(selecOne));
//			driver.findElement(selecOne).click();
//		}
//		logging();
//		Thread.sleep(2000);
//	}
		
		
		

//	@Test
//	public void A5_DashboardNavigation() throws Exception {	
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By dropdown2 = By.id("siteSelect");
//		wait.until(ExpectedConditions.presenceOfElementLocated(dropdown2));
//		driver.findElement(dropdown2).click();
//		logging();
//		Thread.sleep(2000);
//	}
//
//	@Test
//	public void A6_DashboardNavigation() throws Exception {	
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By cper = By.xpath("//*[@id='app']/div/div/div/div/div/div/div/div[1]/div[2]/ul/li/div/div/div");	
//		wait.until(ExpectedConditions.presenceOfElementLocated(cper));
//		driver.findElement(cper).click();
//		logging();
//		Thread.sleep(2000);
//	}
//
//	@Test
//	public void A7_DashboardNavigation() throws Exception {	
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By HUT106538 = By.xpath("//*[@id='app']/div/div/div/div/div/div/div/div[1]/div[2]/ul/li/span/ul/li[2]/div/div[2]/div");
//		wait.until(ExpectedConditions.presenceOfElementLocated(HUT106538));
//		driver.findElement(HUT106538).click();
//		logging();
//		Thread.sleep(2000);
//	}
//
//	@Test
//	public void A8_DashboardNavigation() throws Exception {	
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By ECANLZ106568 = By.xpath("//*[@id='app']/div/div/div/div/div/div/div/div[1]/div[2]/ul/li/span/ul/li[2]/span/ul/li[2]/div/div[2]/div");
//		wait.until(ExpectedConditions.presenceOfElementLocated(ECANLZ106568));
//		driver.findElement(ECANLZ106568).click();
//		logging();
//		Thread.sleep(2000);
//	}
//
//	@Test
//	public void A9_DashboardNavigation() throws Exception {	
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By CFGLOC106569 = By.xpath("//*[@id='app']/div/div/div/div/div/div/div/div[1]/div[2]/ul/li/span/ul/li[2]/span/ul/li[2]/span/ul/li[1]/div/div/div");
//		wait.until(ExpectedConditions.presenceOfElementLocated(CFGLOC106569));
//		driver.findElement(CFGLOC106569).click();
//		logging();
//		Thread.sleep(3000);
//	}
//
//	@Test
//	public void B1_DashboardNavigation() throws Exception {	
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By SENSORS = By.xpath("//*[@id='app']/div/div/ul/li[3]");
//		wait.until(ExpectedConditions.presenceOfElementLocated(SENSORS));
//		driver.findElement(SENSORS).click();
//		logging();
//		Thread.sleep(2000);
//	}
//
//	@Test
//	public void B2_DashboardNavigation() throws Exception {		
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By HOME = By.xpath("//*[@id='app']/div/div/ul/li[1]");
//		wait.until(ExpectedConditions.presenceOfElementLocated(HOME));
//		driver.findElement(HOME).click();
//		logging();
//		Thread.sleep(2000);
//	}

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
