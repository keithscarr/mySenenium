package org.bettelle.neon.is.qa.utilities;

import java.util.logging.Logger;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.Result;

public class JunitHelper extends TestWatcher {

	private static String watchedLog;
	private Logger LOGGER;
	static String newLine = System.getProperty("line.separator");

	public JunitHelper(Logger LOGGER) {
		this.LOGGER = LOGGER;
	}


	@Override
	protected void starting(final Description description) {
		//LOGGER.info("====== STARTED ======" + "("  + description.getMethodName() + ")");
		System.out.println("STARTING: " + description.getMethodName());
		//System.out.println("Number of testcases to execute : " + description.testCount()); 
	}

	@Override
	protected void succeeded(Description description) {
		//LOGGER.info("====== PASSED ======" + "("  + description.getMethodName() + ")");
		System.out.println("PASSED: " + description.getMethodName());
		//watchedLog += description.getDisplayName() + " " + "success!\n";
	}


	@Override
	protected void failed(Throwable e, Description description) {
		//LOGGER.info("====== FAILED ======" + "("  + description.getMethodName() + ")");
		System.out.println("FAILED: " + description.getMethodName());
	}
	
	@Override
	protected void finished(Description description) {
		//LOGGER.info("====== FINISHED ======" + "("  + description.getMethodName() + ")");
		System.out.println("FINISHED: " + description.getMethodName());
		System.out.println(newLine + newLine);
	}
	
	public void testRunFinished(Description result) throws Exception {

		System.out.println("==============================================");
		System.out.println("Test Suite");
		System.out.println("Total tests executed: " + result.testCount() + "\n");
		System.out.println("Total tests passed: " + (result.testCount() -  result.testCount()) + "\n");
		System.out.println("Total tests failed: " + result.testCount() + "\n");
		System.out.println("Total tests ignored: " + result.testCount() + "\n");
		System.out.println("==============================================");
	}
}
