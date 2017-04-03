package org.bettelle.neon.is.qa.rest;

import java.io.FileWriter;
import java.util.logging.Logger;

import org.bettelle.neon.is.qa.utilities.jsonFormater;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class locationMetadata {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	String newLine = System.getProperty("line.separator");
	private static Logger LOGGER = Logger.getLogger("InfoLogging");
	public static String BuildVersion;

	@BeforeClass
	public static void setUpBeforeClass() throws InterruptedException {
		RestAssured.baseURI = "http://localhost:9090/neon-ods/api";
		//RestAssured.baseURI = "http://den-devdocker-1/cdsWebApp";
	}

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		protected void failed(Throwable e, Description description) {
			System.out.println("" + description.getDisplayName() + " failed " + e.getMessage());
			super.failed(e, description);
		}};
		
		@Test 
		public void D11_locationsID () throws Exception {

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
