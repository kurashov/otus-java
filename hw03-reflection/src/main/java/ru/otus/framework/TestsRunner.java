package ru.otus.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import ru.otus.framework.pojo.TestMethods;

/**
 * @author akurashov
 */
public class TestsRunner {
    public static <T> void run(Class<T> clazz) throws Exception {
        Method[] methods = clazz.getDeclaredMethods();

        Constructor<T> constructor = clazz.getConstructor();

        TestMethods testMethods = TestsParser.parseMethods(methods);
        testMethods.getTests()
                .stream()
                //.parallel()
                .forEach(test -> execute(constructor, testMethods.getBefore(), testMethods.getAfter(), test));
    }

    public static <T> void execute(Constructor<T> constructor,
            Method before,
            Method after,
            Method test)
    {
        boolean testPassed = true;
        T testedObject;
        try {
            testedObject = constructor.newInstance();
        } catch (Exception e) {
            TestsOut.printError(e.getMessage());
            return;
        }
        try {
            before.invoke(testedObject);
            test.invoke(testedObject);
        } catch (Exception e) {
            testPassed = false;
        } finally {
            try {
                after.invoke(testedObject);
            } catch (Exception e) {
                TestsOut.printError("Failed during execute @After");
                testPassed = false;
            }
        }
        if (testPassed) {
            TestsOut.printPass(test.getName());
        } else {
            TestsOut.printFail(test.getName());
        }
    }
}
