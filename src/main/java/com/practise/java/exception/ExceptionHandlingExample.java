package com.practise.java.exception;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

// Custom Checked Exception
class CustomCheckedException extends Exception {
    public CustomCheckedException(String message) {
        super(message);
    }
}

// Custom Unchecked Exception
class CustomUncheckedException extends RuntimeException {
    public CustomUncheckedException(String message) {
        super(message);
    }
}

public class ExceptionHandlingExample {

    public static void main(String[] args) {
        ExceptionHandlingExample example = new ExceptionHandlingExample();

        // Demonstrating checked exception handling
        try {
            example.readFile("sample.txt");
        } catch (IOException e) {
            System.out.println("Caught in main: " + e.getMessage());
        }

        // Demonstrating unchecked exception handling
        try {
            example.divide(10, 0);
        } catch (CustomUncheckedException e) {
            System.out.println("Caught custom unchecked exception: " + e.getMessage());
        }

        // Demonstrating custom checked exception
        try {
            example.validateAge(16);
        } catch (CustomCheckedException e) {
            System.out.println("Caught custom checked exception: " + e.getMessage());
        }

        // Demonstrating stream processing with exception handling
        List<Integer> numbers = List.of(1, -2, 3, 4, 5);
        example.processNumbers(numbers);

        // Demonstrating exception handling inside a stream
        List<String> names = List.of("Alice", null, "Bob", "Charlie");
        example.processNames(names);
    }

    // Utility method to handle exceptions in streams
    private <T, R> Function<T, Optional<R>> handleException(FunctionWithException<T, R> function) {
        return input -> {
            try {
                return Optional.ofNullable(function.apply(input));
            } catch (Exception e) {
                System.out.println("Exception in stream: " + e.getMessage());
                return Optional.empty();
            }
        };
    }

    // Method demonstrating checked exception using throws
    public void readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            // Handle IOException
            System.out.println("An IOException occurred: " + e.getMessage());
            throw e; // Re-throwing the exception
        }
    }

    // Method demonstrating unchecked exception
    public void divide(int a, int b) {
        if (b == 0) {
            throw new CustomUncheckedException("Cannot divide by zero"); // Throwing custom unchecked exception
        } else {
            System.out.println("Result: " + (a / b));
        }
    }

    // Method demonstrating custom checked exception
    public void validateAge(int age) throws CustomCheckedException {
        if (age < 18) {
            throw new CustomCheckedException("Age must be 18 or above");
        } else {
            System.out.println("Age is valid");
        }
    }

    // Method demonstrating try-catch-finally with streams
    public void processNumbers(List<Integer> numbers) {
        try {
            List<Integer> evenNumbers = numbers.stream()
                    .filter(n -> {
                        if (n < 0) {
                            throw new IllegalArgumentException("Negative numbers not allowed: " + n);
                        }
                        return n % 2 == 0;
                    })
                    .collect(Collectors.toList());
            System.out.println("Even Numbers: " + evenNumbers);
        } catch (IllegalArgumentException e) {
            System.out.println("An error occurred while processing numbers: " + e.getMessage());
        } finally {
            System.out.println("Stream processing completed.");
        }
    }

    // Method demonstrating exception handling inside a stream
    public void processNames(List<String> names) {
        List<Optional<Integer>> nameLengths = names.stream()
                .map(handleException(name -> {
                    if (name == null) {
                        throw new CustomCheckedException("Name cannot be null");
                    }
                    return name.length();
                }))
                .collect(Collectors.toList());
        System.out.println("Name lengths: " + nameLengths);
    }

    // Functional interface to allow lambda expressions with checked exceptions
    @FunctionalInterface
    public interface FunctionWithException<T, R> {
        R apply(T t) throws Exception;
    }
}