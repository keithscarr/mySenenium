package org.bettelle.neon.is.qa.junit;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class counterClass {
	
	public static class WatchmanTest {
		  private static String watchedLog;

		  @Rule
		  public TestWatcher watchman= new TestWatcher() {
		      @Override
		      protected void failed(Throwable e, Description description) {
		          watchedLog+= description + "\n";
		      }

		      @Override
		      protected void succeeded(Description description) {
		          watchedLog+= description + " " + "success!\n";
		         }
		     };

		  @Test
		  public void fails() {
		      System.out.println("Ivan");
		  }

		  @Test
		  public void succeeds() {
			  
			  System.out.println("Lobo");
		     }
		 }


}
