package org.bettelle.neon.is.qa.utilities;

import java.util.ArrayList;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

public class nestedHelper extends Runner {
	
	private final Runner classRunner;
    private final ArrayList<Runner> childRunners = new ArrayList<>();

    public nestedHelper (final Class<?> testClass) throws InitializationError {
        classRunner = new helperRunner(testClass);

        for (Class<?> aClass : testClass.getDeclaredClasses()) {
            childRunners.add(new nestedHelper(aClass));
        }
    }

    @Override
    public Description getDescription() {
        Description suiteDescription = classRunner.getDescription();

        for (Runner childRunner : childRunners) {
            suiteDescription.addChild(childRunner.getDescription());
        }

        return suiteDescription;
    }

    @Override
    public void run(RunNotifier notifier) {
        classRunner.run(notifier);

        for (Runner childRunner : childRunners) {
            childRunner.run(notifier);
        }
    }

}
