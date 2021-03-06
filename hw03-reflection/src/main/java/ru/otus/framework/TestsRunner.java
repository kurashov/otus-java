package ru.otus.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ru.otus.framework.pojo.TestMethods;
import ru.otus.framework.pojo.TestResult;
import ru.otus.framework.pojo.TestsRunReport;

/**
 * @author akurashov
 */
public class TestsRunner {
    public static <T> void run(Class<T> clazz) throws Exception {
        Method[] methods = clazz.getDeclaredMethods();

        Constructor<T> constructor = clazz.getConstructor();

        TestMethods testMethods = TestsParser.parseMethods(methods);
        List<TestsRunReport> reports = testMethods.getTests()
                .stream()
                .map(test -> execute(constructor, testMethods.getBefore(), testMethods.getAfter(), test))
                .collect(Collectors.toList());

        reports.forEach(TestsRunReport::print);
    }

    public static <T> TestsRunReport execute(Constructor<T> constructor,
            Method before,
            Method after,
            Method test)
    {
        T testedObject;
        try {
            testedObject = constructor.newInstance();
        } catch (Exception e) {
            return new TestsRunReport(test.getName(), TestResult.FAIL, Optional.of(e.getMessage()));
        }
        TestResult testResult = TestResult.PASS;
        Optional<String> messageO = Optional.empty();
        try {
            before.invoke(testedObject);
            test.invoke(testedObject);
        } catch (Exception e) {
            testResult = TestResult.FAIL;
            messageO = Optional.ofNullable(e.getMessage());
        } finally {
            try {
                after.invoke(testedObject);
            } catch (Exception e) {
                testResult = TestResult.FAIL;
                messageO = Optional.of(messageO.orElse("")
                        + Optional.ofNullable(e.getMessage()).orElse("")
                );
            }
        }
        return new TestsRunReport(test.getName(), testResult, messageO);
    }
}
