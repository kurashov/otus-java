package ru.otus.services;

/**
 * @author akurashov
 */
public class CalculationService {
    public int sum(int a, int b) {
        return a + b;
    }

    public int abs(int a) {
        return a > 0 ? a : -1 * a;
    }
}
