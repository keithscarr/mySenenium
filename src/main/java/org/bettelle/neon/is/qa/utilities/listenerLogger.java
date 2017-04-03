package org.bettelle.neon.is.qa.utilities;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class listenerLogger extends TestListenerAdapter {
	
	String newLine = System.getProperty("line.separator");
	
	@Override
	public void onTestStart(ITestResult test_result) {
		log("TEST '" + test_result.getName() + "' STARTED");
	}

	@Override
	public void onTestSuccess(ITestResult test_result) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log("TEST '" + test_result.getName() + "' PASSED");
		//log(test_result.getTestClass());
		//log("Priority of this method is " + test_result.getMethod().getPriority());
		System.out.println(newLine);
	}

	@Override
	public void onTestFailure(ITestResult test_result) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log("TEST '" + test_result.getName() + "' FAILED");
		//log("Priority of this method is " + test_result.getMethod().getPriority());
		System.out.println(newLine);
	}

	@Override
	public void onTestSkipped(ITestResult test_result) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log("TEST '" + test_result.getName() + "' SKIPPED");
		System.out.println(newLine);
	}

	private void log(String methodName) {
		System.out.println(methodName);
	}

//	private void log(IClass testClass) {
//		System.out.println(testClass);
//	}
}
