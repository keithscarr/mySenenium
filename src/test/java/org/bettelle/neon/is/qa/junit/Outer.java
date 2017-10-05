package org.bettelle.neon.is.qa.junit;

import java.util.Arrays;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Outer {
	
	@Parameters()
    public static Iterable<Object[]> data1() {
        return Arrays.asList(new Object[]{}, 
        					 new Object[]{},
        					 new Object[]{}
        );
    }

    @Rule
    public ExternalResource externalResource = new ExternalResource() {
        protected void before() throws Throwable {
  	
        	System.out.println("before"); 	
        }
        
        protected void after() { 
        	
        	System.out.println("after"); 	
        }
    };

    @Test 
    public void A1_test1() { 
        	
        System.out.println("this should print 3 times2"); 
        	
        int[] operator1 = {1, 3, 5};
           for (int i = 0; i < operator1.length; i++) {
        	   
        	   
               System.out.println("This should print 9 times"); 
           }	
    }
    
    @Test
	public void A2_test2() {
    	
    	System.out.println("test2"); 
    	
    	int[] operator1 = {1, 3, 5};
        for (int i = 0; i < operator1.length; i++) {         
            System.out.println("ivan"); 
        }
	}
    
    
}
