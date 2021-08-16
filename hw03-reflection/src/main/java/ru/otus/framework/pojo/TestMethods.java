package ru.otus.framework.pojo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author akurashov
 */
public class TestMethods {
    private Method before;
    private Method after;
    private final List<Method> tests;

    public TestMethods() {
        after = null;
        before = null;
        tests = new ArrayList<>();
    }

    public Method getBefore() {
        return before;
    }

    public void setBefore(Method before) {
        if (this.before != null) {
            throw new IllegalStateException("More than one decelerated as @Before");
        }
        this.before = before;
    }

    public Method getAfter() {
        return after;
    }

    public void setAfter(Method after) {
        if (this.after != null) {
            throw new IllegalStateException("More than one decelerated as @After");
        }
        this.after = after;
    }

    public List<Method> getTests() {
        return tests;
    }

    public void addTest(Method test) {
        tests.add(test);
    }
}
