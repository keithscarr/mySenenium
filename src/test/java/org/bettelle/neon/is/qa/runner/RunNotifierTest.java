package org.bettelle.neon.is.qa.runner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RunNotifierTest {

	private final RunNotifier fNotifier = new RunNotifier();
	
	private static class StartingListener extends RunListener {
		private Description description;
		
		@Override
		public void testStarted(Description description) throws Exception {     
	    	System.out.println("Starting: " + description.getMethodName());
	    }
	}
	
	private static class FailureListener extends RunListener {
		private Failure failure;

		@Override
		public void testFailure(Failure failure) throws Exception {
			this.failure = failure;
			//throw new RuntimeException();
	        //System.out.println(failure.getTrace());
		}
	}

	private static class CountingListener extends RunListener {
		final AtomicInteger fTestStarted = new AtomicInteger(0);

		@Override
		public void testStarted(Description description) throws Exception {
			fTestStarted.incrementAndGet();
			System.out.println("STARTING: " + description.getMethodName());
		}
	}

	@RunListener.ThreadSafe
	private static class ThreadSafeListener extends CountingListener {
	}
	
	@Test
	public void hasNoProblemsWithFailingListeners() { // see issues 209 and 395
		//fNotifier.addListener(new CorruptListener());
		fNotifier.addListener(new FailureListener());
		//fNotifier.addListener(new CorruptListener());
		fNotifier.fireTestRunFinished(new Result());
	}
	
	@Test
	public void addAndRemoveWithThreadSafeListener() {
		ThreadSafeListener listener = new ThreadSafeListener();
		assertThat(listener.fTestStarted.get(), is(0));
		fNotifier.addListener(listener);
		fNotifier.fireTestStarted(null);
		assertThat(listener.fTestStarted.get(), is(1));
		fNotifier.removeListener(listener);
		fNotifier.fireTestStarted(null);
		assertThat(listener.fTestStarted.get(), is(1));
	}
	
//	@Test
//	public void A1_testListener(){
//		ThreadSafeListener listener = new ThreadSafeListener();
//		fNotifier.addFirstListener(listener);
//		System.out.println("ivan1");
//	}
//
//	@Test
//	public void A2_testFalseAssertion(){
//		ThreadSafeListener listener = new ThreadSafeListener();
//		fNotifier.addFirstListener(listener);
//		System.out.println("ivan2");
//	}
//
//	@Test
//	public void A3_testIgnore(){
//		ThreadSafeListener listener = new ThreadSafeListener();
//		fNotifier.addFirstListener(listener);
//		System.out.println("ivan3");
//		assertEquals("ivan", "Lobo");
//	}
//
//	@Test
//	public void A4_testException(){
//		ThreadSafeListener listener = new ThreadSafeListener();
//		fNotifier.addFirstListener(listener);
//		System.out.println("ivan4");
//		fail("Demo for failed test");
//	}
//
//	@Test
//	public void A5_testListener1(){
//		ThreadSafeListener listener = new ThreadSafeListener();
//		fNotifier.addFirstListener(listener);
//		System.out.println("ivan5");
//	}
//
//	@Test
//	public void A6_testListener2(){
//		ThreadSafeListener listener = new ThreadSafeListener();
//		fNotifier.addFirstListener(listener);
//		System.out.println("ivan6");
//	}
//
//	@Test
//	public void A7_testListener3(){
//		ThreadSafeListener listener = new ThreadSafeListener();
//		fNotifier.addFirstListener(listener);
//		System.out.println("ivan7");
//	}

//	@Rule 
//	public TestWatcher watcher = new TestWatcher() { 
//
//		@Override
//		protected void starting(Description description) {
//			System.out.println("starting test case: " + description.getMethodName());
//		};
//
//		@Override
//		protected void failed(Throwable e, org.junit.runner.Description description) {
//			System.err.println("failed test case: " + description.getMethodName());
//			System.err.println("response: " + e.fillInStackTrace());
//		};
//
//		@Override 
//		protected void succeeded(Description description) { 
//			System.out.println("Passed");
//		}
//		@Override
//		protected void finished(Description description) {
//			System.out.println("finished test case: " + description.getMethodName());
//		};
//	};
}
