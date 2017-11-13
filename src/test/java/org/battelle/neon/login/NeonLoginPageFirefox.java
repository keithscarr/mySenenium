package org.battelle.neon.login;

import java.util.Date;
import java.util.logging.Logger;
import org.battelle.neon.is.qa.helper.Ordered;
import org.battelle.neon.is.qa.helper.OrderedRunner;
import org.battelle.neon.is.qa.helper.TestReadConfig;
import org.bettelle.neon.is.qa.utilities.DriverFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jayway.restassured.RestAssured;
@RunWith(value = OrderedRunner.class)
public class NeonLoginPageFirefox {
	
	/** All tests are placed in inner static classes to group them for convenience. The nested classes can be run together **/
	/** or individually. The @RunWith annotation can instantiate all the inner static classes by calling the enclosed.class **/
	static String winHandleBefore;
    static String userName;
    static String userPw;
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
	
	@BeforeClass
	public static void setUpBeforeClass() throws InterruptedException {
		System.out.println("@BeforeClass Method: Initializing " + type + " Driver");
		RestAssured.baseURI = "http://Localhost:9090/neon-ods/api";

		TestReadConfig readUserName =  new TestReadConfig();
		userName = readUserName.getUserName();
		TestReadConfig readUserPassword =  new TestReadConfig();
		userPw = readUserPassword.getUserPassword();
		 driver = DriverFactory.getDriver(type);
         winHandleBefore = driver.getWindowHandle();
         System.out.println(newLine + newLine + winHandleBefore );
         logging();
	}

	private char[] Text;
	
	@Test
	@Ordered(index = 1)
    public void OpenNeonDashboardLoginPage() throws InterruptedException {
    	   //Start with portal log in page 
           driver.get("http://den-devisd-2.ci.neoninternal.org:8080/#/login");
           driver.manage().window().maximize();
           System.out.println("Neon Web Application Title ============= " + driver.getTitle());
           logging();
    }
	
	@Test
	@Ordered(index = 2)
    public void LoginToNeonDashboard() throws InterruptedException {
    	
		driver.findElement(By.xpath("//*[@id=\'username\']")).clear();
		driver.findElement(By.xpath("//*[@id=\'username\']")).sendKeys(userName);

		driver.findElement(By.xpath("//*[@id='password']")).clear();
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys(userPw, Keys.ENTER);
		
		String test = driver.getWindowHandle();
		System.out.println(" " + test);
	    logging();
    }
	
	@Test
	@Ordered(index = 3)
	public void DisplayNeonDropdown() throws Exception {		
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		By dropdown1 = By.id("login-dropdown");
		wait.until(ExpectedConditions.presenceOfElementLocated(dropdown1));
		driver.findElement(dropdown1).click();
//		for (int i = 0; i < 2; i++){
//			driver.findElement(dropdown1).click();
//		}
		String Text = driver.findElement(By.partialLinkText("neon")).getText();
		System.out.println("User Name: " + Text);
		logging();
	}

	@Test
	@Ordered(index = 4)
	public void LogoutFromNeonDashboard() throws Exception {
		driver.findElement(By.xpath("//*[@id=\'app\']/div/div[1]/nav/div/div[2]/ul/li[1]/ul/li")).click();
		System.out.println("User logged out");
		logging();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		LOGGER.info("Closing chromeDriver. Page Title: " + driver.getTitle()); 
		if (driver != null) {
			driver.close();
		}
		System.out.println("@AfterClass Method: " + type + " Driver Closed");
		System.out.println(newLine + newLine);
	}

	public char[] getText() {
		return Text;
	}

	public void setText(char[] text) {
		Text = text;
	}
}
