package ru.otus;

import java.util.List;

import com.google.common.base.Joiner;

/**
 * @author akurashov
 */
public class App {
    public static void main(String... args) {
        List<String> numbers = List.of("1", "2", "3");

        String res = Joiner.on(", ").join(numbers);

        System.out.println("Numbers: " + res);
    }
}
