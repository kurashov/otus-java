package ru.otus;

import ru.otus.framework.TestsRunner;
import ru.otus.tests.CalculationServiceTest;

/**
 * @author akurashov
 */
public class App {
    public static void main(String[] args) throws Exception {
        TestsRunner.run(CalculationServiceTest.class);
    }
}
