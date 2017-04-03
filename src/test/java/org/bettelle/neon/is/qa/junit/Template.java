package org.bettelle.neon.is.qa.junit;

import java.util.Date;
import java.util.logging.Logger;

import org.bettelle.neon.is.qa.utilities.DriverFactory;
import org.bettelle.neon.is.qa.utilities.JunitHelper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.Select;

import com.jayway.restassured.RestAssured;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Template {
	
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
