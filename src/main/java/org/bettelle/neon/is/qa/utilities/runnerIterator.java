package org.bettelle.neon.is.qa.utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.Assert;

public class runnerIterator {
	
	public <T> T instantiate(Class<T> aClass)
            throws IllegalAccessException, InvocationTargetException,
            InstantiationException {
        ArrayList<Class<?>> classes = getNestedClasses(aClass);
        return (T) instantiateNestedClasses(classes);
    }

    private Object instantiateNestedClasses(Iterable<Class<?>> classes)
            throws InstantiationException, IllegalAccessException,
            InvocationTargetException {
        Object[] lastInstance = new Object[0];
        for (Class<?> aClass : classes) {
            final Constructor<?>[] constructors = aClass.getConstructors();
            Assert.assertEquals(1, constructors.length);
            Object instance = constructors[0].newInstance(lastInstance);
            lastInstance = new Object[]{instance};
        }

        return lastInstance[0];
    }

    private ArrayList<Class<?>> getNestedClasses(Class<?> aClass) {
        ArrayList<Class<?>> classes = new ArrayList<>();
        do {
            classes.add(0, aClass);
            aClass = aClass.getDeclaringClass();
        } while (aClass != null);
        return classes;
    }

}
