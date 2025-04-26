package com.practise.java.versions;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class demonstrates the key features introduced in Java 11 LTS.
 * To compile and run:
 * javac Java11Features.java
 * java Java11Features
 * <p>
 * <p>
 * Java 11 Features
 * var in Lambda Parameters: Type inference for lambda parameters
 * New String Methods: isBlank(), lines(), strip(), repeat(), etc.
 * New Files Methods: writeString() and readString()
 * HTTP Client API: Standardized API for HTTP requests
 * Collection.toArray(): Enhanced array creation method
 * Predicate.not(): Negation method for predicates
 * Optional.isEmpty(): Complementary method to isPresent()
 */
public class Java11Features {

    public static void main(String[] args) {
        System.out.println("========== JAVA 11 FEATURES SHOWCASE ==========");

        demonstrateVarInLambdas();
        demonstrateNewStringMethods();
        demonstrateNewFileMethods();
        demonstrateHttpClient();
        demonstrateCollectionToArray();
        demonstratePredicateMethods();
        demonstrateOptionalIsEmpty();
    }

    /**
     * Java 11 allows 'var' in lambda parameter declarations
     */
    private static void demonstrateVarInLambdas() {
        System.out.println("\n----- 'var' in Lambda Parameters -----");

        // Without var - implicitly typed parameters
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(name -> System.out.println("Name: " + name));

        // With var - explicitly typed but with type inference
        // Useful for adding annotations to lambda parameters
        names.forEach((var name) -> System.out.println("Name with var: " + name));

        // Multiple parameters using var (all must use var if any does)
        Map<String, Integer> scores = Map.of("Alice", 95, "Bob", 85, "Charlie", 90);
        scores.forEach((var name, var score) -> {
            System.out.println(name + " scored " + score + " points");
        });

        // You can't mix var with explicit types
        // This would cause a compilation error:
        // scores.forEach((var name, Integer score) -> { ... });
    }

    /**
     * Java 11 added several convenient methods to the String class
     */
    private static void demonstrateNewStringMethods() {
        System.out.println("\n----- New String Methods -----");

        // isBlank() - returns true if string is empty or contains only whitespace
        String blank = "   \t   \n   ";
        System.out.println("Is blank? " + blank.isBlank());  // true
        System.out.println("Is empty? " + blank.isEmpty());  // false (contains whitespace)

        // lines() - splits string by line terminators and returns a stream
        String multiline = "Line 1\nLine 2\nLine 3";
        System.out.println("Lines from string:");
        multiline.lines().forEach(System.out::println);

        // Count lines
        long lineCount = multiline.lines().count();
        System.out.println("Number of lines: " + lineCount);

        // strip(), stripLeading(), stripTrailing() - remove whitespace
        // Similar to trim() but supporting Unicode whitespace
        String text = "  \u2005 Hello World \u2005  ";

        System.out.println("Original: '" + text + "'");
        System.out.println("strip(): '" + text.strip() + "'");
        System.out.println("stripLeading(): '" + text.stripLeading() + "'");
        System.out.println("stripTrailing(): '" + text.stripTrailing() + "'");
        System.out.println("trim(): '" + text.trim() + "'");  // trim() doesn't handle all Unicode whitespace

        // repeat() - repeats the string n times
        String star = "*";
        System.out.println(star.repeat(20));  // Twenty stars

        // Creating a padded string
        String padded = "Center".repeat(1);
        String leftPadding = " ".repeat(10);
        String rightPadding = " ".repeat(10);
        System.out.println("|" + leftPadding + padded + rightPadding + "|");
    }

    /**
     * Java 11 added new convenience methods to the Files class
     */
    private static void demonstrateNewFileMethods() {
        System.out.println("\n----- New Files Methods -----");

        try {
            // writeString - writes a string to a file
            Path path = Files.writeString(
                    Files.createTempFile("java11", ".txt"),
                    "Hello, Java 11!"
            );
            System.out.println("Wrote to file: " + path);

            // readString - reads all content from a file as a string
            String content = Files.readString(path);
            System.out.println("File content: " + content);

            // isSameFile - checks if two paths locate the same file
            Path path2 = Path.of(path.toString());
            System.out.println("Same file? " + Files.isSameFile(path, path2));

            // Delete the temporary file
            Files.delete(path);
            System.out.println("File deleted");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Java 11 standardized the HTTP Client API
     */
    private static void demonstrateHttpClient() {
        System.out.println("\n----- HTTP Client API -----");

        // Create an HTTP client
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)  // Prefer HTTP/2
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        try {
            // Create a GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/get"))
                    .header("User-Agent", "Java 11 HttpClient")
                    .GET()  // GET is default
                    .build();

            // Synchronous send
            System.out.println("Sending request synchronously...");
            HttpResponse<String> response = client.send(
                    request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response status code: " + response.statusCode());
            System.out.println("Response headers: " + response.headers());
            System.out.println("Response body (first 100 chars): " +
                    response.body().substring(0, Math.min(100, response.body().length())) + "...");

            // Asynchronous request
            System.out.println("\nSending request asynchronously...");
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(body -> System.out.println("Async response received!"))
                    .join();  // Wait for completion

            // POST request
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/post"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"Java\",\"version\":11}"))
                    .build();

            HttpResponse<String> postResponse = client.send(
                    postRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("\nPOST response status: " + postResponse.statusCode());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Java 11 added Collection.toArray(IntFunction) to create typed arrays more easily
     */
    private static void demonstrateCollectionToArray() {
        System.out.println("\n----- Collection.toArray(IntFunction) -----");

        List<String> fruits = List.of("Apple", "Banana", "Cherry", "Date");

        // Before Java 11
        String[] oldWay = fruits.toArray(new String[0]);
        System.out.println("Old way: " + Arrays.toString(oldWay));

        // Java 11 way - more concise with constructor reference
        String[] newWay = fruits.toArray(String[]::new);
        System.out.println("New way: " + Arrays.toString(newWay));

        // Working with streams
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        Integer[] numbersArray = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toArray(Integer[]::new);

        System.out.println("Even numbers array: " + Arrays.toString(numbersArray));
    }

    /**
     * Java 11 added new Predicate methods: not
     */
    private static void demonstratePredicateMethods() {
        System.out.println("\n----- Predicate.not() -----");

        List<String> languages = Arrays.asList(
                "Java", "Python", "JavaScript", "", "C#", null, "Go");

        // Using a regular predicate
        Predicate<String> isNullOrEmpty = s -> s == null || s.isEmpty();
        List<String> filteredList = languages.stream()
                .filter(isNullOrEmpty.negate())  // old way, negate the predicate
                .collect(Collectors.toList());

        System.out.println("Filtered (old way): " + filteredList);

        // Using Predicate.not (new in Java 11)
        List<String> filteredList2 = languages.stream()
                .filter(Predicate.not(isNullOrEmpty))  // new way, more readable
                .collect(Collectors.toList());

        System.out.println("Filtered (new way): " + filteredList2);

        // Another example with String.isBlank
        Predicate<String> isNotBlank = Predicate.not(String::isBlank);
        List<String> nonBlankStrings = languages.stream()
                .filter(s -> s != null)  // Filter out nulls first
                .filter(isNotBlank)
                .collect(Collectors.toList());

        System.out.println("Non-blank strings: " + nonBlankStrings);
    }

    /**
     * Java 11 added isEmpty() method to Optional
     */
    private static void demonstrateOptionalIsEmpty() {
        System.out.println("\n----- Optional.isEmpty() -----");

        Optional<String> empty = Optional.empty();
        Optional<String> nonEmpty = Optional.of("Java 11");

        // Before Java 11
        System.out.println("Is empty (old way)? " + !empty.isPresent());
        System.out.println("Is non-empty (old way)? " + nonEmpty.isPresent());

        // Java 11 way - more expressive
        System.out.println("Is empty (new way)? " + empty.isEmpty());
        System.out.println("Is non-empty (new way)? " + !nonEmpty.isEmpty());

        // Practical example
        processValue(empty);
        processValue(nonEmpty);
    }

    private static void processValue(Optional<String> value) {
        // Java 11 style using isEmpty()
        if (value.isEmpty()) {
            System.out.println("No value to process");
        } else {
            System.out.println("Processing: " + value.get());
        }
    }
}
