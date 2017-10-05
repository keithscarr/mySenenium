package org.bettelle.neon.is.qa.rest;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.bettelle.neon.is.qa.utilities.DriverFactory;
import org.bettelle.neon.is.qa.utilities.JunitHelper;
import org.bettelle.neon.is.qa.utilities.jsonFormater;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;

@RunWith(Parameterized.class)
public class locationsID {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	String newLine = System.getProperty("line.separator");
	private static Logger LOGGER = Logger.getLogger("InfoLogging");
	public static String BuildVersion;
	//Fields
	private String iterator;
	private String location_id;
	private String location_value;
	private String domain_value;

	@BeforeClass
	public static void setUpBeforeClass() throws InterruptedException {
		RestAssured.baseURI = "http://Localhost:9090/neon-ods/api";
		//RestAssured.baseURI = "http://den-devdocker-1/cdsWebApp";
	}

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		protected void failed(Throwable e, Description description) {
			System.out.println("" + description.getDisplayName() + " failed " + e.getMessage());
			super.failed(e, description);
	}};

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
	public locationsID(String iterator, String location_id, String location_value) {
		super();
		this.iterator = iterator;
		this.location_id = location_id;
		this.location_value = location_value;
	}

	@Test 
	public void C1_locationsID () throws Exception {

		Integer i = new Integer(iterator).intValue();	
		System.out.println((i + 1) + ") Selecting site from data; " + "ID: " + location_id + ", VALUE: " + location_value);
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
		System.out.println(newLine + newLine);

	}

}
