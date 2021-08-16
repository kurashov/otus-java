package ru.otus.tests;

import org.junit.jupiter.api.Assertions;
import ru.otus.framework.annotations.After;
import ru.otus.framework.annotations.Before;
import ru.otus.framework.annotations.Test;
import ru.otus.services.CalculationService;

/**
 * @author akurashov
 */
public class CalculationServiceTest {
    private CalculationService calculationService;

    @Before
    public void setUp() {
        calculationService = new CalculationService();
        System.out.println("Set up successful");
    }

    @After
    public void cleanUp() {
        System.out.println("Clean up successful");
    }

    @Test
    public void sumTest() {
        Assertions.assertEquals(3, calculationService.sum(1, 2));
    }

    @Test
    public void absTest() {
        Assertions.assertEquals(-1, calculationService.abs(-1));
    }
}
