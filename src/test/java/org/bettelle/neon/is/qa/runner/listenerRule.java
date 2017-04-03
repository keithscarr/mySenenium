package org.bettelle.neon.is.qa.runner;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.MethodSorters;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

public class listenerRule {

	static String newLine = System.getProperty("line.separator");

	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class TestTest1 {

		@Test
		public void A1_testListener(){

			System.out.println("ivan1");
		}

		@Test
		public void A2_testFalseAssertion(){

			System.out.println("ivan2");
		}

		@Test
		public void A3_testIgnore(){

			System.out.println("ivan3");
			assertEquals("ivan", "Lobo");
		}

		@Test
		public void A4_testException(){

			System.out.println("ivan4");
			fail("Demo for failed test");
		}

		@Test
		public void A5_testListener1(){

			System.out.println("ivan5");
		}

		@Test
		public void A6_testListener2(){

			System.out.println("ivan6");
		}

		@Test
		public void A7_testListener3(){

			System.out.println("ivan7");
		}
	}


	private CountingRunListener runTestWithParentRunner(Class<?> testClass) throws InitializationError {
		CountingRunListener listener = new CountingRunListener();
		RunNotifier runNotifier = new RunNotifier();
		runNotifier.addListener(listener);
		ParentRunner runner = new BlockJUnit4ClassRunner(testClass);
		runner.run(runNotifier);
		return listener;
	}

	@Test
	public void parentRunnerTestMethods() throws InitializationError {

		CountingRunListener countingRunListener = runTestWithParentRunner(TestTest1.class);
		System.out.println("==============================================");
		System.out.println("Test Suite");
		System.out.println("Total tests started: " +  countingRunListener.testStarted);
		System.out.println("Total tests finished: " + countingRunListener.testFinished);
		System.out.println("Total tests passed: " + (countingRunListener.testStarted - countingRunListener.testFailure));
		System.out.println("Total tests failed: " +  countingRunListener.testFailure);
		//System.out.println("Total asserts failed: " +  countingRunListener.testAssumptionFailure);
		System.out.println("Total tests ignored: " + countingRunListener.testIgnored);
		System.out.println("==============================================");
	}

	private static class CountingRunListener extends RunListener {

		private int testStarted = 0;
		private int testFinished = 0;
		private int testFailure = 0;
		private int testAssumptionFailure = 0;
		private int testIgnored = 0;
		@Override
		public void testStarted(Description description) throws Exception {
			testStarted++; System.out.println("STARTING: " + description.getMethodName());
		}

		@Override
		public void testFinished(Description description) throws Exception {
			testFinished++; System.out.println("FINISHED: " + description.getMethodName());
			System.out.println(newLine + newLine);
		}

		@Override
		public void testFailure(Failure failure) throws Exception {
			testFailure++; System.out.println("Failed: " + failure.getDescription().getMethodName());
			System.out.println(failure.getTrace());
		}

		@Override
		public void testAssumptionFailure(Failure failure) {
			testAssumptionFailure++; System.out.println("Assertion Failed: " + failure.getDescription().getMethodName());
		}

		@Override
		public void testIgnored(Description description) throws Exception {
			testIgnored++;
			System.out.println("Ignored: " + description.getMethodName());
		}
	}
}
