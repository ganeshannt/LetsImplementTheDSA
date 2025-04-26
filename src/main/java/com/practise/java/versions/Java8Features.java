package com.practise.java.versions;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class demonstrates the key features introduced in Java 8 LTS.
 * To compile and run:
 * javac Java8Features.java
 * java Java8Features
 * <p>
 * Java 8 Features
 * Lambda Expressions: Concise way to represent anonymous functions
 * Method References: Shorthand for lambdas calling specific methods
 * Stream API: Functional-style operations on collections
 * Optional Class: Container for nullable values to avoid NullPointerExceptions
 * Default Methods: Interface methods with implementations
 * New Date/Time API: Modern replacement for legacy date classes
 * Functional Interfaces: Standard interfaces for use with lambdas
 * CompletableFuture: API for asynchronous programming
 */
public class Java8Features {

    public static void main(String[] args) {
        System.out.println("========== JAVA 8 FEATURES SHOWCASE ==========");

        demonstrateLambdaExpressions();
        demonstrateMethodReferences();
        demonstrateStreams();
        demonstrateOptional();
        demonstrateDefaultMethods();
        demonstrateNewDateTimeAPI();
        demonstrateFunctionalInterfaces();
        demonstrateCompletableFuture();
    }

    /**
     * Lambda expressions provide a concise way to represent anonymous functions
     */
    private static void demonstrateLambdaExpressions() {
        System.out.println("\n----- Lambda Expressions -----");

        // Before Java 8: Anonymous inner class
        Runnable oldWay = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from anonymous inner class");
            }
        };

        // With lambda: much more concise
        Runnable withLambda = () -> System.out.println("Hello from lambda expression");

        // Running both
        oldWay.run();
        withLambda.run();

        // Lambda with parameters
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob", "David");

        // Before Java 8
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
        System.out.println("Sorted (old way): " + names);

        // Shuffle and sort with lambda
        Collections.shuffle(names);
        Collections.sort(names, (a, b) -> a.compareTo(b));
        System.out.println("Sorted (lambda): " + names);

        // Multi-line lambda with curly braces
        Consumer<String> printer = s -> {
            String decorated = "*** " + s + " ***";
            System.out.println(decorated);
        };

        names.forEach(printer);
    }

    /**
     * Method references provide a shorthand notation for lambdas calling a specific method
     */
    private static void demonstrateMethodReferences() {
        System.out.println("\n----- Method References -----");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Lambda expression
        names.forEach(name -> System.out.println("Lambda: " + name));

        // Method reference to static method
        names.forEach(System.out::println);

        // Method reference to instance method of an arbitrary object of a particular type
        names.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        // Method reference to instance method of a specific object
        StringJoiner joiner = new StringJoiner(", ");
        names.forEach(joiner::add);
        System.out.println("Joined names: " + joiner);

        // Reference to a constructor
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<StringBuilder> builders = numbers.stream()
                .map(n -> n.toString())
                .map(StringBuilder::new)
                .collect(Collectors.toList());
        System.out.println("StringBuilder objects: " + builders);
    }

    /**
     * The Stream API enables functional-style operations on collections
     */
    private static void demonstrateStreams() {
        System.out.println("\n----- Stream API -----");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eva", "Frank");

        // Filtering
        List<String> filteredNames = names.stream()
                .filter(name -> name.length() > 4)
                .collect(Collectors.toList());
        System.out.println("Names longer than 4 chars: " + filteredNames);

        // Mapping
        List<Integer> nameLengths = names.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("Name lengths: " + nameLengths);

        // FlatMap (multiple streams into one)
        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4, 5),
                Arrays.asList(6)
        );

        List<Integer> allNumbers = listOfLists.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println("Flattened list: " + allNumbers);

        // Reduction (sum, min, max, etc.)
        int sum = allNumbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("Sum of all numbers: " + sum);

        // Other terminal operations
        long count = names.stream()
                .filter(name -> name.startsWith("A"))
                .count();
        System.out.println("Names starting with 'A': " + count);

        // anyMatch, allMatch, noneMatch
        boolean anyLongNames = names.stream().anyMatch(name -> name.length() > 5);
        boolean allShortNames = names.stream().allMatch(name -> name.length() < 10);
        boolean noEmptyNames = names.stream().noneMatch(String::isEmpty);
        System.out.println("Any names longer than 5 chars? " + anyLongNames);
        System.out.println("All names shorter than 10 chars? " + allShortNames);
        System.out.println("No empty names? " + noEmptyNames);

        // Grouping and partitioning
        Map<Integer, List<String>> namesByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Names grouped by length: " + namesByLength);

        Map<Boolean, List<String>> partitioned = names.stream()
                .collect(Collectors.partitioningBy(name -> name.length() > 4));
        System.out.println("Names partitioned by length > 4: " + partitioned);

        // Joining
        String joinedNames = names.stream()
                .collect(Collectors.joining(", ", "Names: [", "]"));
        System.out.println(joinedNames);

        // Parallel streams for performance
        long start = System.currentTimeMillis();
        // Simulating work with sleep
        int parallelSum = IntStream.rangeClosed(1, 100)
                .parallel()
                .map(i -> {
                    try {
                        Thread.sleep(5); // Simulate work
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .sum();
        System.out.println("Parallel sum: " + parallelSum);
        System.out.println("Parallel time: " + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * Optional provides a container object to minimize null pointer exceptions
     */
    private static void demonstrateOptional() {
        System.out.println("\n----- Optional Class -----");

        // Creating Optionals
        Optional<String> empty = Optional.empty();
        Optional<String> nonEmpty = Optional.of("Hello");
        Optional<String> nullable = Optional.ofNullable(getNameMaybeNull());

        // Checking presence
        System.out.println("Empty optional is present? " + empty.isPresent());
        System.out.println("Non-empty optional is present? " + nonEmpty.isPresent());

        // Getting values safely
        String value = nonEmpty.orElse("Default");
        System.out.println("Value or default: " + value);

        // Supplier for expensive default values
        String valueComputed = empty.orElseGet(() -> {
            // This code only executes if optional is empty
            return "Computed Default";
        });
        System.out.println("Computed value: " + valueComputed);

        // Transforming values
        Optional<Integer> length = nonEmpty.map(String::length);
        System.out.println("Length of string if present: " + length.orElse(0));

        // Chaining operations
        String result = nullable
                .map(String::toUpperCase)
                .orElse("NO NAME");
        System.out.println("Transformed nullable: " + result);

        // If-present consumer
        nullable.ifPresent(name -> System.out.println("Name is present: " + name));

        // Filter
        Optional<String> filtered = nonEmpty.filter(s -> s.length() > 10);
        System.out.println("After filter is present? " + filtered.isPresent());
    }

    // Helper method that may return null
    private static String getNameMaybeNull() {
        double random = Math.random();
        return random > 0.5 ? "John" : null;
    }

    /**
     * Default methods allow interfaces to have method implementations
     */
    private static void demonstrateDefaultMethods() {
        System.out.println("\n----- Default Methods -----");

        Vehicle car = new Car();
        car.start();        // Interface default method
        car.accelerate();   // Implemented method
        car.brake();        // Overridden default method

        // Static interface methods
        double kmToMiles = Vehicle.convertKmToMiles(100);
        System.out.println("100 km = " + kmToMiles + " miles");
    }

    /**
     * The new Date-Time API is a major improvement over the old Date and Calendar classes
     */
    private static void demonstrateNewDateTimeAPI() {
        System.out.println("\n----- New Date-Time API -----");

        // Local date, time, and datetime
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        System.out.println("Today: " + today);
        System.out.println("Current time: " + now);
        System.out.println("Current date and time: " + currentDateTime);

        // Creating specific dates and times
        LocalDate independenceDay = LocalDate.of(1776, Month.JULY, 4);
        LocalTime noon = LocalTime.of(12, 0);

        System.out.println("US Independence Day: " + independenceDay);
        System.out.println("Noon: " + noon);

        // Date/time manipulation (immutable objects - operations return new instances)
        LocalDate tomorrow = today.plusDays(1);
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate lastMonth = today.minusMonths(1);

        System.out.println("Tomorrow: " + tomorrow);
        System.out.println("Next week: " + nextWeek);
        System.out.println("Last month: " + lastMonth);

        // Temporal adjusters for common manipulations
        LocalDate nextMonday = today.with(DayOfWeek.MONDAY);
        if (today.getDayOfWeek() == DayOfWeek.MONDAY) {
            nextMonday = nextMonday.plusWeeks(1);
        }
        System.out.println("Next Monday: " + nextMonday);

        // Time zones
        ZoneId newYork = ZoneId.of("America/New_York");
        ZonedDateTime newYorkTime = currentDateTime.atZone(newYork);
        ZonedDateTime tokyoTime = newYorkTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));

        System.out.println("New York time: " + newYorkTime);
        System.out.println("Tokyo time: " + tokyoTime);

        // Instant - point in time
        Instant timestamp = Instant.now();
        System.out.println("Current timestamp: " + timestamp);

        // Period and Duration
        Period oneYearOneMonthOneDay = Period.of(1, 1, 1);
        LocalDate futureDate = today.plus(oneYearOneMonthOneDay);
        System.out.println("Future date: " + futureDate);

        Duration twoHours = Duration.ofHours(2);
        LocalTime laterTime = now.plus(twoHours);
        System.out.println("Two hours from now: " + laterTime);

        // Formatting dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy HH:mm");
        System.out.println("Formatted date: " + currentDateTime.format(formatter));

        // Parsing dates
        LocalDate parsedDate = LocalDate.parse("2023-12-25");
        LocalDateTime parsedDateTime = LocalDateTime.parse("2023-12-25T10:15:30");

        System.out.println("Parsed date: " + parsedDate);
        System.out.println("Parsed date-time: " + parsedDateTime);
    }

    /**
     * Java 8 introduced several functional interfaces that are commonly used with lambdas
     */
    private static void demonstrateFunctionalInterfaces() {
        System.out.println("\n----- Functional Interfaces -----");

        // Supplier: no input, produces output
        Supplier<Double> randomSupplier = Math::random;
        System.out.println("Supplier result: " + randomSupplier.get());

        // Consumer: accepts input, no output
        Consumer<String> printer = System.out::println;
        printer.accept("Hello from Consumer");

        // BiConsumer: accepts two inputs, no output
        BiConsumer<String, Integer> repeater = (s, n) -> {
            for (int i = 0; i < n; i++) {
                System.out.println(s);
            }
        };
        repeater.accept("Echo", 3);

        // Function: transforms input to output
        Function<String, Integer> lengthFunc = String::length;
        System.out.println("Length of 'Hello': " + lengthFunc.apply("Hello"));

        // BiFunction: two inputs, one output
        BiFunction<String, String, String> concatFunc = (a, b) -> a + b;
        System.out.println("Concat: " + concatFunc.apply("Hello, ", "World!"));

        // Predicate: input -> boolean
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 42 even? " + isEven.test(42));

        // BiPredicate: two inputs -> boolean
        BiPredicate<String, String> areEqual = String::equals;
        System.out.println("Are 'a' and 'A' equal? " + areEqual.test("a", "A"));

        // UnaryOperator: specialization of Function where input and output are the same type
        UnaryOperator<String> toUpperCase = String::toUpperCase;
        System.out.println("Uppercase: " + toUpperCase.apply("hello"));

        // BinaryOperator: specialization of BiFunction where all types are the same
        BinaryOperator<Integer> adder = Integer::sum;
        System.out.println("Sum of 5 and 3: " + adder.apply(5, 3));

        // Composing functions
        Function<Integer, Integer> multiplyBy2 = n -> n * 2;
        Function<Integer, Integer> add10 = n -> n + 10;

        Function<Integer, Integer> multiplyBy2ThenAdd10 = add10.compose(multiplyBy2);
        Function<Integer, Integer> add10ThenMultiplyBy2 = add10.andThen(multiplyBy2);

        System.out.println("(5 * 2) + 10 = " + multiplyBy2ThenAdd10.apply(5));
        System.out.println("(5 + 10) * 2 = " + add10ThenMultiplyBy2.apply(5));
    }

    /**
     * CompletableFuture provides a powerful way to handle asynchronous programming
     */
    private static void demonstrateCompletableFuture() {
        System.out.println("\n----- CompletableFuture -----");

        // Create a completed future
        CompletableFuture<String> completed = CompletableFuture.completedFuture("Result");
        System.out.println("Completed future result: " + completed.join());

        // Asynchronous computation
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Async result";
        });

        // Chain transformations
        CompletableFuture<String> transformed = future
                .thenApply(String::toUpperCase)
                .thenApply(result -> "Transformed: " + result);

        System.out.println("Transformed result: " + transformed.join());

        // Combining futures
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<String> combined = future1.thenCombine(future2, (s1, s2) -> s1 + " " + s2);
        System.out.println("Combined result: " + combined.join());

        // Handling errors
        CompletableFuture<String> errorHandled = CompletableFuture
                .supplyAsync(() -> {
                    if (true) throw new RuntimeException("Simulated error");
                    return "Won't reach here";
                })
                .exceptionally(ex -> "Error caught: " + ex.getMessage());

        System.out.println(errorHandled.join());

        // Composing dependent futures
        CompletableFuture<Double> price = CompletableFuture.supplyAsync(() -> 9.99);
        CompletableFuture<Double> tax = price.thenCompose(p ->
                CompletableFuture.supplyAsync(() -> p * 0.08));

        CompletableFuture<Double> total = price.thenCombine(tax, Double::sum);
        System.out.println("Total price with tax: $" + String.format("%.2f", total.join()));
    }

    // Interface with default methods
    interface Vehicle {
        // Static methods in interfaces
        static double convertKmToMiles(double km) {
            return km * 0.621371;
        }

        void accelerate();

        // Default method with implementation
        default void start() {
            System.out.println("Starting vehicle");
        }

        default void brake() {
            System.out.println("Default braking behavior");
        }
    }

    // Implementation of Vehicle interface
    static class Car implements Vehicle {
        @Override
        public void accelerate() {
            System.out.println("Car is accelerating");
        }

        @Override
        public void brake() {
            System.out.println("Car specific braking behavior");
        }
    }
}

