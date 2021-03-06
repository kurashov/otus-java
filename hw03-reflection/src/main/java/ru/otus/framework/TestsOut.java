package ru.otus.framework;

/**
 * @author akurashov
 */
public class TestsOut {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static void printGreen(String testName) {
        System.out.printf(ANSI_GREEN + "Test %s passed%n" + ANSI_RESET, testName);
    }

    public static void printRed(String testName) {
        System.out.printf(ANSI_RED + "Test %s failed%n" + ANSI_RESET, testName);
    }
}
