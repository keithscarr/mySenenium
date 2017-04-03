package org.bettelle.neon.is.qa.utilities;

import java.util.logging.Logger;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class JUnitExecutionListener extends RunListener {
	
	private Logger LOGGER;
	static String newLine = System.getProperty("line.separator");
	
//	public JUnitExecutionListener(Logger LOGGER) {    
//		this.LOGGER = LOGGER;
//	}

    public void testRunStarted(Description description) throws Exception {       
    	System.out.println("Number of tests to execute: " + description.testCount());
    }

    public void testRunFinished(Result result) throws Exception {
    	
    	System.out.println("==============================================");
    	System.out.println("Test Suite");
        System.out.println("Total tests executed: " + result.getRunCount());
        System.out.println("Total tests passed: " + (result.getRunCount() -  result.getFailureCount()));
        System.out.println("Total tests failed: " + result.getFailureCount());
        System.out.println("Total tests ignored: " + result.getIgnoreCount());
        System.out.println("==============================================");
    }

    public void testStarted(Description description) throws Exception {      
    	System.out.println("STARTING: " + description.getMethodName());
    }

    public void testFinished(Description description) throws Exception {       
    	System.out.println("FINISHED: " + description.getMethodName());
        System.out.println(newLine + newLine);
    }
    
    public void testSuccess(Result success) throws Exception {
        System.out.println("Passed: " + success.wasSuccessful());
        //System.out.println(failure.getTrace());
    }

    public void testFailure(Failure failure) throws Exception {
        System.out.println("Failed: " + failure.getDescription().getMethodName());
        System.out.println(failure.getTrace());
    }

    public void testAssumptionFailure(Failure failure) {     
    	System.out.println("Failed: " + failure.getDescription().getMethodName());
    }

    public void testIgnored(Description description) throws Exception {       
    	System.out.println("Ignored: " + description.getMethodName());
    }
}
