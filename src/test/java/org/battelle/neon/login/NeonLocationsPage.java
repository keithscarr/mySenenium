package org.battelle.neon.login;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.battelle.neon.is.qa.helper.TestReadConfig;
import org.bettelle.neon.is.qa.utilities.DriverFactory;
import org.battelle.neon.is.qa.helper.Ordered;
import org.battelle.neon.is.qa.helper.OrderedRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jayway.restassured.RestAssured;

@RunWith(value = OrderedRunner.class)
public class NeonLocationsPage {
	
	/** All tests are placed in inner static classes to group them for convenience. The nested classes can be run together **/
	/** or individually. The @RunWith annotation can instantiate all the inner static classes by calling the enclosed.class **/
	static String winHandleBefore;
    static String userName;
    static String userPw;
	public static WebDriver driver;
	protected static final int TIMEOUT = 30;
	private static char[] Text = null;
	static String newLine = System.getProperty("line.separator");
	private static Logger LOGGER = Logger.getLogger("InfoLogging");
	static DriverFactory.BrowserType type = DriverFactory.BrowserType.CHROME;
	EventFiringWebDriver slowDriver = new EventFiringWebDriver(driver);
	
	 public static void logging() {
         LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
         for (LogEntry entry : logEntries) {
                System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
         }
  }

		public static Logger getLOGGER() {
			return LOGGER;
		}

		public static void setLOGGER(Logger lOGGER) {
			LOGGER = lOGGER;
		}
		
//	@Before
//	public void setup() throws InterruptedException {
//	driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
//    driver.manage().window().maximize();
//	
//	}
		
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

	@Test
	@Ordered(index = 1)
    public void OpenNeonDashboardPage() throws InterruptedException {
    	driver.get("http://den-devisd-2.ci.neoninternal.org:8080/#/login");
        driver.manage().window().maximize();
        System.out.println("Neon Web Application Title ============= " + driver.getTitle());
           logging();
    }
	
	@Test
	@Ordered(index = 2)
    public void LoginToNeonDashboard() throws InterruptedException {
		// wait for 2.5 seconds
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    public void EnterEddyCovariancePage() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"sciselection\"]")).click();
		String text = driver.findElement(By.xpath("//*[@id=\"sciselection\"]")).getText();
		System.out.println("Page Selected: " + text);
           logging();
    }
		
	@Test
	@Ordered(index = 5)
    public void NavigateToLocationsPage() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div/div/div[2]/ul/li[2]/a")).click();
		String text = driver.findElement(By.xpath("/html/body/div/div/div[2]/ul/li[2]/a")).getText();
		System.out.println("Tab selected: " + text);
           logging();
    }
	
	@Test
	@Ordered(index = 6)
	public void DisplaySiteDropdown() throws Exception {		
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		By dropdown = By.id("siteSelect");
		wait.until(ExpectedConditions.presenceOfElementLocated(dropdown));
		driver.findElement(dropdown).click();
		// wait for 2.5 seconds
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logging();
	}

	@Test
	@Ordered(index = 7)
	public void SelectSiteDropdown() throws Exception {		
		//		driver.findElement(By.id("siteSelect")).click();
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div/div[2]/div[1]/div/div/div[1]/select/option[66]")).click();
		String text = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div/div[2]/div[1]/div/div/div[1]/select/option[66]")).getText();
		System.out.println("Site selected from drop-down: " + text);
		// wait for 2.5 seconds
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logging();
	}

	public static char[] getText() {
		return Text;
	}

	@Test
	@Ordered(index = 8)
	public void SelectSite() throws Exception {		
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div/div[2]/div[2]/div/ul/li/div/div[2]/div")).click();
		String text = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div/div[2]/div[2]/div/ul/li/div/div[2]/div")).getText();
		System.out.println("Site selected: " + text);
		// wait for 2.5 seconds
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logging();
	}

	@Test
	@Ordered(index = 9)
	public void VerifySensorID() throws Exception {		
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div/div[2]/div[3]/div[3]/div/div[1]/div/div/div[1]/div[1]/table/thead/tr/th[1]")).getText();
		System.out.println("Sensors table successfully loaded with data");
		// wait for 2.5 seconds
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logging();
	}
		
	@Test
	@Ordered(index = 10)
	public void LastPage() throws Exception {		
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div/div[2]/div[3]/div[3]/div/div[1]/div/div/div[2]/div/div/div[2]/ul/li[7]/a")).click();
		// wait for 2.5 seconds
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logging();
	}
	
	@Test
	@Ordered(index = 11)
	public void FirstPage() throws Exception {		
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div/div[2]/div[3]/div[3]/div/div[1]/div/div/div[2]/div/div/div[2]/ul/li[1]/a")).click();
		// wait for 2.5 seconds
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logging();
	}
	
	@Test
	@Ordered(index = 12)
	public void TowerSelect() throws Exception {		
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div/div[2]/div[2]/div/ul/li/span/ul/li[4]/div/div[2]/div")).click();
		String text = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div/div[2]/div[2]/div/ul/li/span/ul/li[4]/div/div[2]/div")).getText();
		System.out.println("Tower selected: " + text);
		// wait for 2.5 seconds
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logging();
	}
	
	@Test
	@Ordered(index = 13)
	public void NextPage() throws Exception {		
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div/div[2]/div[3]/div[3]/div/div[1]/div/div/div[2]/div/div/div[2]/ul/li[5]/a")).click();
		// wait for 2.5 seconds
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logging();
	}
	
	@Test
	@Ordered(index = 14)
	public void PreviousPage() throws Exception {		
		driver.findElement(By.xpath("//*[@id=\"locationExtrasTabs-pane-1\"]/div/div/div[2]/div/div/div[2]/ul/li[1]/a")).click();
		// wait for 2.5 seconds
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logging();
	}
	
	@Test
	@Ordered(index = 15)
	public void PageNumber4() throws Exception {		
		driver.findElement(By.xpath("//*[@id=\"locationExtrasTabs-pane-1\"]/div/div/div[2]/div/div/div[2]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		logging();
	}
	
	@Test
	@Ordered(index = 16)
	public void PageNumber2() throws Exception {		
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div/div[2]/div[3]/div[3]/div/div[1]/div/div/div[2]/div/div/div[2]/ul/li[3]/a")).click();
		Thread.sleep(2000);
		logging();
	}
	
	@Test
	@Ordered(index = 17)
	public void PageDropDown50() throws Exception {		
		driver.findElement(By.id("pageDropDown")).click();
		driver.findElement(By.xpath("//*[@id=\"locationExtrasTabs-pane-1\"]/div/div/div[2]/div/div/div[1]/span/ul/li[4]/a")).click();
		Thread.sleep(2000);
		logging();
	}

	@Test
	@Ordered(index = 18)
	public void AssetUIDDescending() throws Exception {
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div/div[2]/div[3]/div[3]/div/div[1]/div/div/div[1]/div[1]/table/thead/tr/th[5]/span")).click();
		Thread.sleep(2000);
		String text = driver.findElement(By.xpath("//*[@id=\"locationExtrasTabs-pane-1\"]/div/div/div[1]/div[2]/table/tbody/tr[1]/td[5]")).getText();
		System.out.println("Descending AssetUID: " + text);
		logging();
	}
	
	@Test
	@Ordered(index = 19)
	public void AssetUIDAscending() throws Exception {
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div/div[2]/div[3]/div[3]/div/div[1]/div/div/div[1]/div[1]/table/thead/tr/th[5]/span")).click();
		Thread.sleep(2000);
		String text = driver.findElement(By.xpath("//*[@id=\"locationExtrasTabs-pane-1\"]/div/div/div[1]/div[2]/table/tbody/tr[1]/td[5]")).getText();
		System.out.println("Ascending AssetUID: " + text);
		logging();
	}
	
	@Test
	@Ordered(index = 20)
	public void FilterRemovedColumn() throws Exception {
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div/div[2]/div[3]/div[3]/div/div[1]/div/div/div[1]/div[1]/table/thead/tr/th[7]/div/input")).sendKeys("2017");
		String text = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div/div[2]/div[3]/div[3]/div/div[1]/div/div/div[1]/div[2]/table/tbody/tr[1]/td[7]")).getText();
		System.out.println("Date in top cell: " + text);
		// wait for 2.5 seconds
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logging();
	}
	
	@Test
	@Ordered(index = 21)
	public void LogoutFromNeonDashboard() throws Exception {
		driver.findElement(By.id("login-dropdown")).click();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/nav/div/div[2]/ul/li[1]/ul/li/a")).click();
		System.out.println("User logged out");
		// wait for 2.5 seconds
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		// wait for 2.5 seconds
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
	public char[] getText1() {
		return Text;
	}

	public void setText(char[] text) {
		Text = text;
	}
}