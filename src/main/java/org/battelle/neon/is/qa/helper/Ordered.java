package org.battelle.neon.is.qa.helper;

import java.lang.annotation.*;

/**
 * An interface to control the order of execution for JUnit test methods.
 */
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface Ordered
{
	/**
	 * The index of the test relative to the other test indices.
	 * 
	 * @return
	 * 		The index of the test.
	 */
	public int index() default -1;
}
