package org.bettelle.neon.is.qa.utilities;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class executionLIstener extends RunListener {
	
	static String newLine = System.getProperty("line.separator");
	
	private static class CountingRunListener extends RunListener {
		private int testStarted = 0;
		private int testFinished = 0;
		private int testFailure = 0;
		private int testAssumptionFailure = 0;
		private int testIgnored = 0;

		@Override
		public void testStarted(Description description) throws Exception {
			testStarted++;
			System.out.println("STARTING: " + description.getMethodName());
		}

		@Override
		public void testFinished(Description description) throws Exception {
			testFinished++;
			System.out.println("FINISHED: " + description.getMethodName());
			System.out.println(newLine + newLine);
		}

		@Override
		public void testFailure(Failure failure) throws Exception {
			testFailure++;
			System.out.println("Failed: " + failure.getDescription().getMethodName());
			System.out.println(failure.getTrace());
		}

		@Override
		public void testAssumptionFailure(Failure failure) {
			testAssumptionFailure++;
		}

		@Override
		public void testIgnored(Description description) throws Exception {
			testIgnored++;
			System.out.println("Ignored: " + description.getMethodName());
		}
	}

}
