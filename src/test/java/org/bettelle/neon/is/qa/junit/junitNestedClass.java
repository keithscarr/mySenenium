package org.bettelle.neon.is.qa.junit;

import java.util.PriorityQueue;

import org.bettelle.neon.is.qa.utilities.nestedHelper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

@RunWith(nestedHelper.class)
public class junitNestedClass {
	
	static int count;
	protected PriorityQueue<String> subject;

	
	@BeforeClass
	  public static void beforeClass() {
	    count = 0;
	  }

    @Before
    public void setup() throws Exception {
        subject = new PriorityQueue<String>();
        count++;
    }

    @Test
    public void shouldStartEmpty() throws Exception {
    	
    	System.out.println("1");
        assertThat(subject.isEmpty(), is(true));
    }

    public class WhenEmpty {
        @Before
        public void setup() throws Exception {
            subject.clear();
            count++;
        }

        @Test
        public void shouldHaveZeroItems() throws Exception {
        	
        	System.out.println("2");
            assertThat(subject.size(), is(0));
        }

        @Test
        public void shouldKneelWhenTheQueenPasses() throws Exception {
        	
        	System.out.println("3");
        }

        @Test(expected = NullPointerException.class)
        public void shouldThrowWhenAddingNull() throws Exception {
        	
        	System.out.println("4");
            subject.add(null);
        }
    }

    public class WithOneItem {
        @Before
        public void setup() throws Exception {
            subject.add("Item");
            count++;
        }

        @Test
        public void shouldHaveOneItem() throws Exception {
        	
        	System.out.println("5");
            assertThat(subject.size(), is(1));
        }

        public class Clear {
            @Before
            public void setup() throws Exception {
                subject.clear();
                count++;
            }

            @Test
            public void shouldEmptyTheQueue() throws Exception {
            	
            	System.out.println("6");
                assertThat(subject.size(), is(0));
            }
            
            public class Clear2 {
                @Before
                public void setup() throws Exception {
                    subject.clear();
                    count++;
                }

                @Test
                public void shouldEmptyTheQueue() throws Exception {
                	
                	System.out.println("7");
                    assertThat(subject.size(), is(0));
                }
            }
        }
    }
    
    @AfterClass
    public static void printCount() {
      System.out.println(count + " tests.");
    }

}
