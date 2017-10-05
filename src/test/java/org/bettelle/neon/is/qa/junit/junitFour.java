package org.bettelle.neon.is.qa.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class junitFour extends TestCase { 

	    @Test
	    public void addShouldAddTwoNumbers() {
	        int[] operator1 = {1, 3, 5};
	        int[] operator2 = {2, 7, 9};

	        for (int i = 0; i < operator1.length; i++) {
	            int actualResult = add(operator1[i], operator2[i]);
	           
	            System.out.println("before"); 
	        }
	    }

	    private int add(int i, int j) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Test
	    public void subtractShouldSubtractTwoNumbers() {
	        int[] operator1 = {5, 8, 7};
	        int[] operator2 = {1, 2, 10};

	        for (int i = 0; i < operator1.length; i++) {
	        	 int actualResult = add(operator1[i], operator2[i]);
	        	 System.out.println("after"); 
	        }
	    }
}
