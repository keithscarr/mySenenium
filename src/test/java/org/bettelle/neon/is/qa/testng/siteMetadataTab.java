package org.bettelle.neon.is.qa.testng;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.bettelle.neon.is.qa.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;

import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Listeners(org.bettelle.neon.is.qa.utilities.listenerLogger.class)
public class siteMetadataTab {
	
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
			By sitemetadatatab = By.xpath("//*[@id='app']/div/div/ul/li[11]/a");
			wait.until(ExpectedConditions.presenceOfElementLocated(sitemetadatatab));
			driver.findElement(sitemetadatatab).click();
			logging();
		}

		@Test
		public void A3_DashboardNavigation() throws Exception {		
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By dropdown1 = By.id("siteSelect");
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdown1));
			driver.findElement(dropdown1).click();
			logging();
		}

		@Test
		public void A4_DashboardNavigation() throws Exception {		
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By selecOne = By.xpath("//*[@id='siteSelect']/option[31]");
			wait.until(ExpectedConditions.presenceOfElementLocated(selecOne));
			driver.findElement(selecOne).click();
			logging();
		}
	
		@Test
		public void A5_DashboardNavigation() throws Exception {		
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By dropdown1 = By.id("siteSelect");
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdown1));
			driver.findElement(dropdown1).click();
			logging();
		}
		
		@Test
		public void A6_DashboardNavigation() throws Exception {		
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By selecTwo = By.xpath("//*[@id='siteSelect']/option[32]");
			wait.until(ExpectedConditions.presenceOfElementLocated(selecTwo));
			driver.findElement(selecTwo).click();
			logging();
		}
		
		@Test
		public void A7_DashboardNavigation() throws Exception {		
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By dropdown1 = By.id("siteSelect");
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdown1));
			driver.findElement(dropdown1).click();
			logging();
		}
		
		@Test
		public void A8_DashboardNavigation() throws Exception {		
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By selecThree = By.xpath("//*[@id='siteSelect']/option[33]");
			wait.until(ExpectedConditions.presenceOfElementLocated(selecThree));
			driver.findElement(selecThree).click();
			logging();
		}
		
//		@Test
//		public void A9_DashboardNavigation() throws Exception {		
//			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
//			By dropdown1 = By.id("timezone");
//			wait.until(ExpectedConditions.presenceOfElementLocated(dropdown1));
//			String TimeZone =  driver.findElement(dropdown1).getAttribute("value");
//			System.out.println("Default Tinme Zone: " + TimeZone);
//			Assert.assertEquals("US Mountain Standard", TimeZone);
//			logging();
//		}
		
		@Test
		public void B1_DashboardNavigation() throws Exception {		
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By dropdown1 = By.id("zoneTime");
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdown1));
			driver.findElement(dropdown1).click();
			logging();
		}
		
		@Test
		public void B2_DashboardNavigation() throws Exception {		
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By selecOne = By.xpath("//*[@id='zoneTime']/option[7]");
			wait.until(ExpectedConditions.presenceOfElementLocated(selecOne));
			driver.findElement(selecOne).click();
			String expectedTimeZone = driver.findElement(selecOne).getAttribute("value");
			//By dropdown1 = By.id("timezone");
			//String actualTimeZone = driver.findElement(dropdown1).getAttribute("value");
			System.out.println("Expected Tinme Zone: " + expectedTimeZone);
			//System.out.println("Actual Tinme Zone: " + actualTimeZone);
			Assert.assertEquals("America/Argentina/Buenos_Aires", expectedTimeZone);
			logging();
		}
		
		@Test
		public void B3_DashboardNavigation() throws Exception {		
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By MeasTow = By.id("lvlMeasTow");
			wait.until(ExpectedConditions.presenceOfElementLocated(MeasTow));
			driver.findElement(MeasTow).click();
			driver.findElement(MeasTow).clear();
			driver.findElement(MeasTow).sendKeys("asdfasdfadsf");
			logging();
		}
		
		@Test
		public void B4_DashboardNavigation() throws Exception {		
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By MeasTow = By.xpath("//*[@id='app']/div/div/div/div/div/div/div[2]/div[2]/form/fieldset/div[3]/div/div/span/b[1]");
			wait.until(ExpectedConditions.presenceOfElementLocated(MeasTow));
			//Select dropdown = new Select(driver.findElement(By.id("designation")));
			//driver.findElement(MeasTow).s.selectByValue("prog");
			for (int i = 0; i < 3; i++){
				 //click the button
				driver.findElement(MeasTow).click();
				 //wait 1 seconds
				  Thread.sleep(1000);
				}
			logging();
		}
		
		@Test
		public void B5_DashboardNavigation() throws Exception {		
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By DistMeas = By.id("distZaxsLvlMeas");
			wait.until(ExpectedConditions.presenceOfElementLocated(DistMeas));
			driver.findElement(DistMeas).click();
			logging();
		}
		
		@Test
		public void B6_DashboardNavigation() throws Exception {		
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By MeasTow = By.xpath("//*[@id='app']/div/div/div/div/div/div/div[2]/div[2]/form/fieldset/div[4]/div/div/span/b[1]");
			wait.until(ExpectedConditions.presenceOfElementLocated(MeasTow));
			for (int i = 0; i < 3; i++){
				 //click the button
				driver.findElement(MeasTow).click();
				 //wait 1 seconds
				  Thread.sleep(1000);
				}
			logging();
		}
		
		@Test
		public void B7_DashboardNavigation() throws Exception {		
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			By saveButton = By.id("saveButton");
			wait.until(ExpectedConditions.presenceOfElementLocated(saveButton));
			driver.findElement(saveButton).click();
			logging();
		}
		
		@AfterClass
		public static void tearDownAfterClass() {
			System.out.println("@AfterClass Method: Terminating " + type + " Driver"); 
			if (driver != null) {
				driver.quit();
			}
			System.out.println(newLine + newLine);
		}
}
