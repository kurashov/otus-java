package ru.otus.framework.pojo;

import java.util.Optional;

import ru.otus.framework.TestsOut;

/**
 * @author akurashov
 */
public class TestsRunReport {
    private final String testName;
    private final TestResult testResult;
    private Optional<String> messageO;

    public Optional<String> getMessageO() {
        return messageO;
    }

    public TestsRunReport setMessageO(Optional<String> messageO) {
        this.messageO = messageO;
        return this;
    }

    public TestsRunReport(String testName, TestResult testResult, Optional<String> messageO) {
        this.testName = testName;
        this.testResult = testResult;
        this.messageO = messageO;
    }

    public void print() {
        if (testResult.equals(TestResult.PASS)) {
            TestsOut.printGreen(testName);
        }
        if (testResult.equals(TestResult.PASS)) {
            TestsOut.printRed(testName);
            messageO.ifPresent(TestsOut::printRed);
        }
    }
}
