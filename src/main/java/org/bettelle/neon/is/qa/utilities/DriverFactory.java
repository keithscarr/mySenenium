package org.bettelle.neon.is.qa.utilities;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {
	
	public enum BrowserType{
		CHROME,
		EXPLORER,
		FIREFOX,
		EDGE
	}
	public static WebDriver getDriver(BrowserType type){

		WebDriver driver=null;
		switch(type){
		case CHROME:
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
			DesiredCapabilities logsGC = DesiredCapabilities.chrome();
		    LoggingPreferences logPrefsGC = new LoggingPreferences();
		    logPrefsGC.enable(LogType.BROWSER, Level.ALL);
		    logsGC.setCapability(CapabilityType.LOGGING_PREFS, logPrefsGC);
		    driver = new ChromeDriver(logsGC);
			//driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			break;
		case EXPLORER:
			System.setProperty("webdriver.ie.driver", "src/main/resources/drivers/IEDriverServer.exe");
			DesiredCapabilities logsIE = DesiredCapabilities.internetExplorer();
		    LoggingPreferences logPrefsIE = new LoggingPreferences();
		    logPrefsIE.enable(LogType.BROWSER, Level.ALL);
		    logsIE.setCapability(CapabilityType.LOGGING_PREFS, logPrefsIE);
		    driver = new InternetExplorerDriver(logsIE);
			//driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			break;
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
			DesiredCapabilities logsMF = DesiredCapabilities.firefox();
		    LoggingPreferences logPrefsMF = new LoggingPreferences();
		    logPrefsMF.enable(LogType.BROWSER, Level.ALL);
		    logsMF.setCapability(CapabilityType.LOGGING_PREFS, logPrefsMF);
		    driver = new FirefoxDriver(logsMF);
			//driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			break;
		case EDGE: 
			System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/MicrosoftWebDriver.exe");
			DesiredCapabilities logsME = DesiredCapabilities.edge();
		    LoggingPreferences logPrefsME = new LoggingPreferences();
		    logPrefsME.enable(LogType.BROWSER, Level.ALL);
		    logsME.setCapability(CapabilityType.LOGGING_PREFS, logPrefsME);
		    driver = new InternetExplorerDriver(logsME);
			driver = new EdgeDriver();
			//driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			break;
		default:
			System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
			DesiredCapabilities logsDefault = DesiredCapabilities.chrome();
		    LoggingPreferences logPrefsDefault = new LoggingPreferences();
		    logPrefsDefault.enable(LogType.BROWSER, Level.ALL);
		    logsDefault.setCapability(CapabilityType.LOGGING_PREFS, logPrefsDefault);
		    driver = new ChromeDriver(logsDefault);
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			break;
		}
		return driver;
	}

}
