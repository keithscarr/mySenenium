package org.battelle.neon.is.qa.helper;

//
// Battelle.org 04-APR-2017
// This is a base test designed to test log on, reach a polygon form, and logout 
// Expect all tests to PASS, but be aware, someone might have changed the values
// TODO: MDR - hide password
//

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestReadConfig {

	public String getUserName() throws InterruptedException {
           Properties prop = new Properties();
           InputStream input = null;
           String userName = null;
 
        	try {
        		input = new FileInputStream(".config.properties");
        		// load a properties file
        		prop.load(input);

        		// get the property value and print it out
        		userName = prop.getProperty("UserName");

        	} catch (IOException ex) {
        		ex.printStackTrace();
        	} finally {
       		if (input != null) {
       			try {
       				input.close();
       			} catch (IOException e) {
       				e.printStackTrace();
       			}
       		}
       	}
			return userName;
    }
	
	public String getUserPassword() throws InterruptedException {
        Properties prop = new Properties();
        InputStream input = null;
        String userPw = null;

     	try {
     		input = new FileInputStream(".config.properties");
     		// load a properties file
     		prop.load(input);

     		// get the property value and print it out
     		userPw   = prop.getProperty("UserPassword");
    		
     	} catch (IOException ex) {
     		ex.printStackTrace();
     	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
     	return userPw;
	}
}
