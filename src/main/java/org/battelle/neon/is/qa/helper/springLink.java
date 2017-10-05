package org.battelle.neon.is.qa.helper;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;

import org.battelle.neon.is.qa.helper.springLinkException;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.slf4j.Logger;
import org.junit.runner.Description;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestContextManager;
import java.util.Objects;
import javax.annotation.Nullable;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * The rule approach allows to have other runners like for parameterized data driven testing.
 */

public class springLink extends TestWatcher {
	
	 private static final Logger LOG = LoggerFactory.getLogger(springLink.class);

	  private static final ThreadLocal<Object> TEST_INSTANCE = new ThreadLocal<>();
	  private static final ThreadLocal<Method> TEST_METHOD = new ThreadLocal<>();
	  private static final Pattern PLAIN_METHOD_NAME_PATTERN = Pattern.compile("^(?<plain>[^\\[]+).*$");
	  private static final String PLAIN_METHOD_GROUP = "plain";

	  private final TestContextManager testContextManager;

	  private springLink(@Nonnull final Class<?> testClass) {
	    testContextManager = new TestContextManager(testClass);
	  }

	  public static springLink forClass(final Class<?> testClass) {
	    return new springLink(testClass);
	  }

	  @Nonnull
	  private static Object getTestInstance() {
	    return Objects.requireNonNull(TEST_INSTANCE.get(), "Test Initialization failure: Test Instance unknown.");
	  }

	  @Nonnull
	  private static Method getTestMethod() {
	    return Objects.requireNonNull(TEST_METHOD.get(), "Test Method undetermined. Wrong execution order?");
	  }

	  @Override
	  protected void starting(final Description description) {
	    try {
	      if (description.isSuite()) {
	        LOG.debug("Preparing test class {}.", description.getClassName());
	        testContextManager.beforeTestClass();
	      } else if (description.isTest()) {
	        String methodName = plainMethodName(description);
	        LOG.debug("Preparing test {}#{}.", description.getClassName(), methodName);
	        TEST_METHOD.set(description.getTestClass().getMethod(methodName));
	        testContextManager.beforeTestMethod(getTestInstance(), getTestMethod());
	      }
	    } catch (final Exception e) {
	      throw new springLinkException(e);
	    }
	  }

	  private static String plainMethodName(Description description) {
	   
	    String methodName = description.getMethodName();
	    Matcher matcher = PLAIN_METHOD_NAME_PATTERN.matcher(methodName);
	    if (matcher.matches()) {
	      methodName = matcher.group(PLAIN_METHOD_GROUP);
	    }
	    return methodName;
	  }

	  @Override
	  protected void failed(final Throwable e, final Description description) {
	    LOG.debug("Handling failure for {}.", description.getDisplayName());
	    afterTest(e, description);
	  }

	  @Override
	  protected void succeeded(final Description description) {
	    LOG.debug("Handling success for {}.", description.getDisplayName());
	    afterTest(null, description);
	  }

	  @Override
	  protected void finished(final Description description) {
	    LOG.debug("Handling finish for {}.", description.getDisplayName());
	    if (description.isSuite()) {
	      try {
	        testContextManager.afterTestClass();
	      } catch (final Exception e) {
	        throw new springLinkException("Failure executing TestContextManager's afterTestClass.", e);
	      }
	    }
	  }

	  private void afterTest(@Nullable final Throwable e, final Description description) {
	    if (description.isTest()) {
	      try {
	        testContextManager.afterTestMethod(getTestInstance(), getTestMethod(), e);
	      } catch (final Exception afterTestMethodFailure) {
	        throw new springLinkException("Failure calling TestContextManager's afterTestMethod.", afterTestMethodFailure);
	      }
	    }
	  }

	  public TestRule forInstance(final Object testInstance) {
	    try {
	      TEST_INSTANCE.set(testInstance);
	      testContextManager.prepareTestInstance(testInstance);
	    } catch (final Exception e) {
	      throw new springLinkException("Failed to prepare test instance.", e);
	    }
	    return this;
	  }
}
