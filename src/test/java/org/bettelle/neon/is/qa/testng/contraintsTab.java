package org.bettelle.neon.is.qa.testng;

import static org.testng.Assert.assertEquals;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.bettelle.neon.is.qa.utilities.DriverFactory;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Listeners(org.bettelle.neon.is.qa.utilities.listenerLogger.class)
public class contraintsTab {
	

	//Attributes: CHROME, EXPLORER, FIREFOX, EDGE
	public static WebDriver driver;
	protected static final int TIMEOUT = 30;
	static String newLine = System.getProperty("line.separator");
	static DriverFactory.BrowserType type = DriverFactory.BrowserType.CHROME;
	
	public static void logging() {
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logEntries) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
		}
	}

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
		By constraintstab = By.id("sel1");
		wait.until(ExpectedConditions.presenceOfElementLocated(constraintstab));
		driver.findElement(constraintstab).click();
		logging();
	}
	
	@Test
	public void A3_DashboardNavigation() throws Exception {				
		
		new Select(driver.findElement(By.id("sel1"))).selectByVisibleText("ANGEL");
	}

	@Test
	public void A4_DashboardNavigation() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		By constraintstab = By.xpath("//*[@id='app']/div/div/ul/li[7]/a");
		wait.until(ExpectedConditions.presenceOfElementLocated(constraintstab));
		driver.findElement(constraintstab).click();
		logging();
	}

	@Test
	public void A5_DashboardNavigation() throws Exception {		
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		By dropdown1 = By.id("siteSelect");
		wait.until(ExpectedConditions.presenceOfElementLocated(dropdown1));
		for (int i = 0; i < 2; i++){
			driver.findElement(dropdown1).click();
		}
		logging();
	}
	
	@Test
	public void A6_DashboardNavigation() throws Exception {				
		
		new Select(driver.findElement(By.id("siteSelect"))).selectByVisibleText("STEI");
	}
	
	@Test
	public void A7_DashboardNavigation() throws Exception {				
		
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		By relativePosition = By.xpath("//*[@id='app']/div/div/div/div/div/div/div[2]/div[2]/ul/li/div/div[2]/div");
		wait.until(ExpectedConditions.textToBePresentInElementLocated(relativePosition, "STEI"));
		String rpText = driver.findElement(relativePosition).getText();
		System.out.println("Printing " + rpText);
		assertEquals("STEI", rpText);
		driver.findElement(relativePosition).click();
	}
	
	@Test
	public void A8_DashboardNavigation() throws Exception {				
		
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		By locationLabel = By.id("location");
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locationLabel, "STEI"));
		String locationText = driver.findElement(locationLabel).getText();
		System.out.println("Location Label " + locationText);
		assertEquals("STEI", locationText);
	}
	
//	@Test
//	public void A4_siteInformation() throws Exception {
//
//		LOGGER.info("REQUEST");
//		Response response = RestAssured
//				.given()
//				.header("Accept", "application/json, text/plain, */*")
//				.log().all()
//				.when()
//				.get("/locations/siteinfo");
//		LOGGER.info("RESPONSE");
//		response.then()
//		.log().all()
//		//.log().status() 
//		//.log().headers()
//		//.log().ifError()
//		.statusCode(200);
//		
//		//String responseString = response.asString();
//		//FileWriter file = new FileWriter("src/test/resources/data/location.site.info.data.json");
//		//file.write(responseString.toString());file.flush();file.close();
//	}
	
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
	
	@DataProvider(name = "Provider")
	public static Iterator<Object[]> getDataFromFile() {
		
		JsonPath jsonPath = new JsonPath(new File("src/test/resources/data/location.site.info.data.json"));
		List<Object> jsonObject1 = jsonPath.get("id");
		List<Object> jsonObject2 = jsonPath.get("value");
		
		List<Object[]> data = new ArrayList<Object[]>();
		for (int i = 0; i < jsonObject1.size(); i++) {
			data.add(new Object[] {new Integer(i).toString(),
					jsonObject1.get(i),
					jsonObject2.get(i)
			});
		}
		return data.listIterator();
	}
	

	@Test (dataProvider = "Provider")
	public void A7_DashboardNavigation(String iterator, String test_id, String test_name) throws Exception {				
		
		Integer i = new Integer(iterator).intValue();	
		System.out.println((i + 1) + ") Selecting site from data; " + "ID: " + test_id + ", NAME: " + test_name);
		new Select(driver.findElement(By.id("siteSelect"))).selectByVisibleText(test_name);
		
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		new Select(driver.findElement(By.id("siteSelect"))).selectByVisibleText(test_name);
		By relativePosition = By.xpath("//*[@id='app']/div/div/div/div/div/div/div[2]/div[2]/ul/li/div/div[2]/div");
		wait.until(ExpectedConditions.textToBePresentInElementLocated(relativePosition, test_name));
		assertEquals(test_name, driver.findElement(relativePosition).getText());
		driver.findElement(relativePosition).click();
	}
	
//	@Test (dataProvider = "Provider")
//	public void A8_DashboardNavigation(String iterator, String test_id, String test_name) throws Exception {				
//		
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
		
//	}
	
//	@Test (dataProvider = "Provider")
//	public void A6_parameterizedData(String iterator, String test_id, String test_name) {
//		
//		Integer i = new Integer(iterator).intValue();
//		Select  selecting1 = new Select(driver.findElement(By.id("siteSelect")));
//		//Select  selecting2 = new Select(driver.findElement(By.id("siteSelect")));
//		List <WebElement> elementcount = selecting1.getOptions();
//		//System.out.println(elementcount.get(i).getText());
//		
//		System.out.println(iterator + ") Data from json file; " + "id: " +
//							test_id + ", name: " + test_name);
//		System.out.println(iterator + ") Data from web page; " + "id: " + 
//							selecting1.getOptions().get(i).getAttribute("value") + 
//							", name: " + elementcount.get(i).getText());
//		//assertEquals(test_id, selecting1.getOptions().get(i).getAttribute("value"));
//		//assertEquals(test_name, elementcount.get(i).getText());
//		logging();
//	}
	

//	@Test
//	public void A5_DashboardNavigation() throws Exception {				
//		WebElement site_element = driver.findElement(By.id("siteSelect"));
//		Select  selecting = new Select(site_element);
//		List <WebElement> elementcount = selecting.getOptions();
//		for(int i=2 ; i < 3; i++) {
//			String value = elementcount.get(i).getText();
//        	selecting.selectByVisibleText(value);
//        	Thread.sleep(1000);
//        	WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//        	By dropdown1 = By.id("siteSelect");
//        	wait.until(ExpectedConditions.presenceOfElementLocated(dropdown1));
//        	driver.findElement(dropdown1).click();
//        	Thread.sleep(1000);
//        	By sitelabel = By.xpath("//*[@id='app']/div/div/div/div/div/div/div[2]/div[2]/div/ul/li/div/div[2]/div");
//        	wait.until(ExpectedConditions.presenceOfElementLocated(sitelabel));
//        	driver.findElement(sitelabel).click();
//        	Thread.sleep(1000);
//		}
//	}
	
//	@Test
//	public void A5_DashboardNavigation() throws Exception {				
//		WebElement site_element = driver.findElement(By.id("siteSelect"));
//		Select  selecting2 = new Select(site_element);
//		List <WebElement> elementcount = selecting2.getOptions();
//		for(int i=2 ; i < selecting2.getOptions().size(); i++) 
//		System.out.println(elementcount.get(i).getText());
//	}
	
//	@Test
//	public void A4_DashboardNavigation() throws Exception {				
//		WebElement site_element = driver.findElement(By.id("siteSelect"));
//		Select  selecting = new Select(site_element);
//		List <WebElement> elementcount = selecting.getOptions();
//		System.out.println(elementcount);
//		for(int i=2 ; i < 10; i++) {
//			String value = elementcount.get(i).getText();
//        	selecting.selectByVisibleText(value);
//        	Thread.sleep(1000);
//		}
//	}

//	@Test
//	public void A5_DashboardNavigation() throws Exception {		
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By selecOne = By.xpath("//*[@id='siteSelect']/option[2]");
//		wait.until(ExpectedConditions.presenceOfElementLocated(selecOne));
//		driver.findElement(selecOne).click();
//		logging();
//	}
//	
//	@Test
//	public void A6_DashboardNavigation() throws Exception {	
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By dropdown2 = By.id("siteSelect");
//		wait.until(ExpectedConditions.presenceOfElementLocated(dropdown2));
//		driver.findElement(dropdown2).click();
//		logging();
//	}
//	
//	@Test
//	public void A7_DashboardNavigation() throws Exception {		
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By sitelabel = By.xpath("//*[@id='app']/div/div/div/div/div/div/div[2]/div[2]/ul/li/div/div[2]/div");
//    	wait.until(ExpectedConditions.presenceOfElementLocated(sitelabel));
//    	driver.findElement(sitelabel).click();
//    	logging();
//	}
	
//*[@id="app"]/div/div/div/div/div/div/div[2]/div[2]/ul/li/div/div[2]/div
//*[@id='app']/div/div/div/div/div/div/div[2]/div[2]/div/ul/li/div/div[2]/div
//	@Test
//	public void A6_DashboardNavigation() throws Exception {	
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By selectlocation = By.xpath("//*[@id='app']/div/div/div/div/div/div/div[2]/div[1]/div[2]/ul/li/div/div[2]/div");	
//		wait.until(ExpectedConditions.presenceOfElementLocated(selectlocation));
//		driver.findElement(selectlocation).click();
//		logging();
//	}
//
//	@Test
//	public void A7_DashboardNavigation() throws Exception {	
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By HUT106538 = By.xpath("//*[@id='app']/div/div/div/div/div/div/div[2]/div[1]/div[2]/ul/li/span/ul/li[1]/div/div/div");
//		wait.until(ExpectedConditions.presenceOfElementLocated(HUT106538));
//		driver.findElement(HUT106538).click();
//		logging();
//	}
//
//	@Test
//	public void A8_DashboardNavigation() throws Exception {	
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By ECANLZ106568 = By.id("sensorSelect");
//		wait.until(ExpectedConditions.presenceOfElementLocated(ECANLZ106568));
//		driver.findElement(ECANLZ106568).click();
//		logging();
//	}
//
//	@Test
//	public void A9_DashboardNavigation() throws Exception {	
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By CFGLOC106569 = By.xpath("//*[@id='sensorSelect']/option[2]");
//		wait.until(ExpectedConditions.presenceOfElementLocated(CFGLOC106569));
//		driver.findElement(CFGLOC106569).click();
//		logging();
//	}
//
//	@Test
//	public void B1_DashboardNavigation() throws Exception {	
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By selectstream = By.id("streamSelect");
//		wait.until(ExpectedConditions.presenceOfElementLocated(selectstream));
//		driver.findElement(selectstream).click();
//		logging();
//	}
//
//	@Test
//	public void B2_DashboardNavigation() throws Exception {		
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By sDate = By.id("startDate");
//		wait.until(ExpectedConditions.presenceOfElementLocated(sDate));
//		driver.findElement(sDate).click();
//		driver.findElement(sDate).sendKeys("02", "02", "2017");
//		logging();
//	}
//
//	@Test
//	public void B3_DashboardNavigation() throws Exception {		
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By eDate = By.id("endDate");
//		wait.until(ExpectedConditions.presenceOfElementLocated(eDate));
//		driver.findElement(eDate).click();
//		driver.findElement(eDate).sendKeys("02", "03", "2017");
//		logging();
//	}
//
//	@Test
//	public void B4_DashboardNavigation() throws Exception {		
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By rangeMin = By.id("minRng");
//		wait.until(ExpectedConditions.presenceOfElementLocated(rangeMin));
//		driver.findElement(rangeMin).click();
//		driver.findElement(rangeMin).sendKeys("100");
//		logging();
//	}
//
//	@Test
//	public void B5_DashboardNavigation() throws Exception {		
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By rangeMax = By.id("maxRng");
//		wait.until(ExpectedConditions.presenceOfElementLocated(rangeMax));
//		driver.findElement(rangeMax).click();
//		driver.findElement(rangeMax).sendKeys("0.5");
//		logging();
//	}
//
//	@Test
//	public void B6_DashboardNavigation() throws Exception {		
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By stepDiff = By.id("step");
//		wait.until(ExpectedConditions.presenceOfElementLocated(stepDiff));
//		driver.findElement(stepDiff).click();
//		driver.findElement(stepDiff).sendKeys("0.005");
//		logging();
//	}
//
//	@Test
//	public void B7_DashboardNavigation() throws Exception {		
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By perstDiff = By.id("pers");
//		wait.until(ExpectedConditions.presenceOfElementLocated(perstDiff));
//		driver.findElement(perstDiff).click();
//		driver.findElement(perstDiff).sendKeys("1E-07");
//		logging();
//	}
//
//	@Test
//	public void B8_DashboardNavigation() throws Exception {		
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By perstTime = By.id("persWindow");
//		wait.until(ExpectedConditions.presenceOfElementLocated(perstTime));
//		driver.findElement(perstTime).click();
//		driver.findElement(perstTime).sendKeys("10");
//		logging();
//	}
//
//	@Test
//	public void B9_DashboardNavigation() throws Exception {		
//		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//		By saveButton = By.id("save");
//		wait.until(ExpectedConditions.presenceOfElementLocated(saveButton));
//		driver.findElement(saveButton).click();
//		logging();
//	}

	@AfterClass
	public static void C1_tearDownAfterClass() {
		System.out.println("@AfterClass Method: Terminating " + type + " Driver"); 
		if (driver != null) {
			driver.quit();
		}
	}

}
