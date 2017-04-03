package org.bettelle.neon.is.qa.runner;

import static org.junit.Assert.*;

import java.awt.print.Book;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.runners.Parameterized;
import org.bettelle.neon.is.qa.utilities.MyRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.jayway.restassured.path.json.JsonPath;

public class TestBookDAO {

	    @Parameter(0)
	    public String title;
	    @Parameter(1)
	    public String author;

	    /** Tests that run only once. */
	    public static class SingleTests {

	    	@Test
		    public void shouldKneelWhenTheQueenPasses() throws Exception {
		    	
		    	System.out.println("3");
		    }
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
}
