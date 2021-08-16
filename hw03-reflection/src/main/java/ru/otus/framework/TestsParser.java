package ru.otus.framework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import ru.otus.framework.annotations.After;
import ru.otus.framework.annotations.Before;
import ru.otus.framework.annotations.Test;
import ru.otus.framework.pojo.TestMethods;

/**
 * @author akurashov
 */
public class TestsParser {
    public static TestMethods parseMethods(Method[] methods) {
        TestMethods testMethods = new TestMethods();
        for (Method method : methods) {
            if (hasAnnotation(method, Test.class)) {
                testMethods.addTest(method);
            }
            if (hasAnnotation(method, Before.class)) {
                testMethods.setBefore(method);
                continue;
            }
            if (hasAnnotation(method, After.class)) {
                testMethods.setAfter(method);
            }
        }
        return testMethods;
    }
    
    private static boolean hasAnnotation(Method method, Class annotation) {
        Annotation[] annotations = method.getDeclaredAnnotations();
        return Arrays.stream(annotations).anyMatch(a -> a.annotationType().equals(annotation));
    }
}
