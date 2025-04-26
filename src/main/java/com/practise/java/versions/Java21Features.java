//package com.practise.java.versions;
//
//
//import java.time.Duration;
//import java.time.Instant;
//import java.util.*;
//import java.util.concurrent.Executors;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.function.Predicate;
//import java.util.stream.IntStream;
//
/// **
// * This class demonstrates the key features introduced in Java 21 LTS.
// * To compile and run:
// * javac --enable-preview --release 21 Java21Features.java
// * java --enable-preview Java21Features
// *
// * Note: Some features like String Templates are preview features in Java 21
// * and require the --enable-preview flag.
// *
// *
// * Java 21 Features
// * Virtual Threads: Lightweight threading for high-throughput apps
// * Pattern Matching for Switch: Enhanced switch expressions with patterns
// * Record Patterns: Powerful nested pattern matching
// * Sequenced Collections: Standardized operations for ordered collections
// * String Templates: More flexible string interpolation (preview)
// */
//public class Java21Features {
//
//    public static void main(String[] args) {
//        System.out.println("========== JAVA 21 FEATURES SHOWCASE ==========");
//
//        demonstrateVirtualThreads();
//        demonstratePatternMatchingForSwitch();
//        demonstrateRecordPatterns();
//        demonstrateSequencedCollections();
//        demonstrateStringTemplates();
//    }
//
//    /**
//     * Virtual Threads (Project Loom) provide lightweight threading for high-throughput
//     * concurrent applications
//     */
//    private static void demonstrateVirtualThreads() {
//        System.out.println("\n----- Virtual Threads -----");
//
//        // Creating a single virtual thread
//        Thread vt = Thread.startVirtualThread(() -> {
//            System.out.println("Hello from a virtual thread!");
//            System.out.println("Is virtual: " + Thread.currentThread().isVirtual());
//        });
//
//        // Wait for it to complete
//        try {
//            vt.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Compare platform vs virtual thread performance
//        System.out.println("\nComparing platform vs virtual threads for many tasks:");
//
//        demonstrateThreadPerformance(false); // platform threads
//        demonstrateThreadPerformance(true);  // virtual threads
//
//        // Using the ExecutorService for virtual threads
//        System.out.println("\nUsing a virtual thread per task executor:");
//        AtomicInteger counter = new AtomicInteger();
//
//        Instant start = Instant.now();
//        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
//            // Submit 10,000 tasks
//            for (int i = 0; i < 10_000; i++) {
//                executor.submit(() -> {
//                    try {
//                        Thread.sleep(1); // Simulate a small task
//                        counter.incrementAndGet();
//                    } catch (InterruptedException e) {
//                        Thread.currentThread().interrupt();
//                    }
//                });
//            }
//        } // executor.close() called implicitly, waits for tasks
//        Instant end = Instant.now();
//
//        System.out.println("Completed " + counter.get() + " tasks in " +
//                Duration.between(start, end).toMillis() + "ms");
//
//        // Demonstrate scoped values (another Java 21 feature related to virtual threads)
//        try {
//            vt = Thread.ofVirtual().start(() -> {
//                System.out.println("\nStructured concurrency allows for better organization");
//                System.out.println("Virtual threads are perfect for I/O-bound applications");
//                System.out.println("They enable millions of concurrent connections with minimal resources");
//            });
//            vt.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void demonstrateThreadPerformance(boolean useVirtualThreads) {
//        final int THREAD_COUNT = 10_000;
//        final String threadType = useVirtualThreads ? "virtual" : "platform";
//
//        System.out.println("\nCreating " + THREAD_COUNT + " " + threadType + " threads...");
//
//        AtomicInteger completed = new AtomicInteger(0);
//        Thread[] threads = new Thread[THREAD_COUNT];
//
//        Instant start = Instant.now();
//
//        // Create threads
//        for (int i = 0; i < THREAD_COUNT; i++) {
//            Runnable task = () -> {
//                try {
//                    Thread.sleep(10); // Simulate a small task
//                    completed.incrementAndGet();
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            };
//
//            threads[i] = useVirtualThreads ?
//                    Thread.ofVirtual().unstarted(task) :
//                    Thread.ofPlatform().unstarted(task);
//        }
//
//        // Start threads
//        for (Thread thread : threads) {
//            thread.start();
//        }
//
//        // Wait for all threads to finish
//        for (Thread thread : threads) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        Instant end = Instant.now();
//        long duration = Duration.between(start, end).toMillis();
//
//        System.out.println("All " + threadType + " threads completed in " + duration + "ms");
//        System.out.println("Tasks completed: " + completed.get());
//    }
//
//    /**
//     * Pattern Matching for Switch enhances switch expressions and statements
//     * to work with patterns, including type patterns
//     */
//    private static void demonstratePatternMatchingForSwitch() {
//        System.out.println("\n----- Pattern Matching for Switch -----");
//
//        // Create a variety of objects to test with
//        Object[] objects = {
//                42,                 // Integer
//                "Hello",           // String
//                1.5,               // Double
//                List.of(1, 2, 3),  // List
//                new int[]{1, 2, 3},// Array
//                null,              // null
//                true,              // Boolean
//                new Person("Alice", 30), // Custom record from earlier example
//                Map.of("key", "value")  // Map
//        };
//
//        // Process each object with the new pattern matching switch
//        for (Object obj : objects) {
//            String result = formatValue(obj);
//            System.out.println("Object: " + obj + " -> " + result);
//        }
//
//        // Demonstrate guarded patterns
//        System.out.println("\nGuarded patterns:");
//        for (Object obj : new Object[] { 5, 15, 25, -10 }) {
//            String category = classifyNumber(obj);
//            System.out.println(obj + " -> " + category);
//        }
//
//        // Nested pattern matching
//        System.out.println("\nNested patterns with records:");
//        Object nestedStructure = new Address("123 Main St", "Springfield", "IL", "62701");
//        System.out.println(describeObject(nestedStructure));
//
//        nestedStructure = new Person("Bob", 42);
//        System.out.println(describeObject(nestedStructure));
//    }
//
//    private static String formatValue(Object obj) {
//        return switch (obj) {
//            case null -> "It's null";
//            case Integer i -> "Integer with value " + i;
//            case String s -> "String with length " + s.length();
//            case Double d when d > 1.0 -> "Double greater than 1.0: " + d;
//            case Double d -> "Small double: " + d;
//            case List<?> list -> "List with " + list.size() + " elements";
//            case int[] arr -> "Int array of length " + arr.length;
//            case Person p -> "Person named " + p.name() + ", age " + p.age();
//            case Map<?, ?> map -> "Map with " + map.size() + " entries";
//            default -> "Unrecognized object: " + obj.getClass().getSimpleName();
//        };
//    }
//
//    private static String classifyNumber(Object obj) {
//        return switch (obj) {
//            case Integer i when i < 0 -> "Negative integer";
//            case Integer i when i >= 0 && i < 10 -> "Single-digit positive integer";
//            case Integer i when i >= 10 && i < 100 -> "Double-digit positive integer";
//            case Integer i -> "Large positive integer";
//            default -> "Not an integer";
//        };
//    }
//
//    private static String describeObject(Object obj) {
//        return switch (obj) {
//            case Address(String street, String city, String state, String zip) ->
//                    "Address in " + city + ", " + state + " with zip " + zip;
//
//            case Person(String name, Integer age) when age >= 18 ->
//                    "Adult named " + name;
//
//            case Person(String name, Integer age) ->
//                    "Minor named " + name;
//
//            default -> "Unknown object type";
//        };
//    }
//
//    /**
//     * Record Patterns allow for powerful nested pattern matching with records
//     */
//    private static void demonstrateRecordPatterns() {
//        System.out.println("\n----- Record Patterns -----");
//
//        // Create some nested record structures
//        Point p1 = new Point(10, 20);
//        Point p2 = new Point(30, 40);
//        ColoredPoint cp = new ColoredPoint(p1, "red");
//        Rectangle rect = new Rectangle(p1, p2);
//        Triangle tri = new Triangle(new Point(0, 0), new Point(10, 0), new Point(5, 8));
//        Line line = new Line(p1, p2);
//
//        // Various objects to test pattern matching
//        Object[] objects = {p1, cp, rect, tri, line};
//
//        for (Object obj : objects) {
//            // Basic record pattern
//            if (obj instanceof Point(int x, int y)) {
//                System.out.println("Point at coordinates (" + x + ", " + y + ")");
//            }
//
//            // Nested pattern matching
//            processShape(obj);
//
//            // With more complex logic
//            analyzeGeometry(obj);
//        }
//
//        // Using with switch patterns
//        System.out.println("\nShape details:");
//        for (Object obj : objects) {
//            System.out.println(getShapeDetails(obj));
//        }
//
//        // Enhanced shape calculations
//        System.out.println("\nShape calculations:");
//        for (Object obj : objects) {
//            if (obj instanceof Shape) {
//                Shape shape = (Shape) obj;
//                System.out.println(shape + " area: " + calculateArea(shape));
//            }
//        }
//    }
//
//    // Helper records for demonstrating Record Patterns
//    record Point(int x, int y) {
//        double distanceFromOrigin() {
//            return Math.sqrt(x * x + y * y);
//        }
//    }
//
//    record ColoredPoint(Point point, String color) {}
//    record Rectangle(Point topLeft, Point bottomRight) implements Shape {
//        int width() { return bottomRight.x() - topLeft.x(); }
//        int height() { return bottomRight.y() - topLeft.y(); }
//    }
//    record Triangle(Point p1, Point p2, Point p3) implements Shape {}
//    record Line(Point start, Point end) {
//        double length() {
//            int dx = end.x() - start.x();
//            int dy = end.y() - start.y();
//            return Math.sqrt(dx * dx + dy * dy);
//        }
//    }
//
//    interface Shape {}
//
//    private static void processShape(Object obj) {
//        if (obj instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2))) {
//            System.out.println("Rectangle from (" + x1 + "," + y1 + ") to (" +
//                    x2 + "," + y2 + ") with width " + (x2 - x1) +
//                    " and height " + (y2 - y1));
//        } else if (obj instanceof ColoredPoint(Point(int x, int y), String color)) {
//            System.out.println("Colored point at (" + x + "," + y + ") with color " + color);
//        } else if (obj instanceof Triangle(Point p1, Point p2, Point p3)) {
//            System.out.println("Triangle with points at " + p1 + ", " + p2 + ", and " + p3);
//        }
//    }
//
//    private static void analyzeGeometry(Object obj) {
//        if (obj instanceof Line(Point(int x1, int y1), Point(int x2, int y2))) {
//            double length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
//            boolean isHorizontal = y1 == y2;
//            boolean isVertical = x1 == x2;
//
//            if (isHorizontal) {
//                System.out.println("Horizontal line with length " + length);
//            } else if (isVertical) {
//                System.out.println("Vertical line with length " + length);
//            } else {
//                double slope = (double)(y2 - y1) / (x2 - x1);
//                System.out.println("Diagonal line with length " + length + " and slope " + slope);
//            }
//        }
//    }
//
//    private static String getShapeDetails(Object obj) {
//        return switch (obj) {
//            case Point p -> "Simple point at (" + p.x() + ", " + p.y() + ")";
//
//            case ColoredPoint(Point p, String color) ->
//                    String.format("Colored point at (%d, %d) in %s", p.x(), p.y(), color);
//
//            case Rectangle(Point(var x1, var y1), Point(var x2, var y2)) ->
//                    String.format("Rectangle from (%d, %d) to (%d, %d), area: %d",
//                            x1, y1, x2, y2, (x2 - x1) * (y2 - y1));
//
//            case Triangle(Point p1, Point p2, Point p3) -> {
//                double a = distanceBetween(p1, p2);
//                double b = distanceBetween(p2, p3);
//                double c = distanceBetween(p3, p1);
//                double s = (a + b + c) / 2;  // semi-perimeter
//                double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));  // Heron's formula
//                yield String.format("Triangle with sides %.2f, %.2f, %.2f and area %.2f",
//                        a, b, c, area);
//            }
//
//            case Line(Point start, Point end) -> {
//                double length = distanceBetween(start, end);
//                yield String.format("Line from %s to %s with length %.2f", start, end, length);
//            }
//
//            default -> "Unknown shape";
//        };
//    }
//
//    private static double distanceBetween(Point p1, Point p2) {
//        int dx = p2.x() - p1.x();
//        int dy = p2.y() - p1.y();
//        return Math.sqrt(dx * dx + dy * dy);
//    }
//
//    private static double calculateArea(Shape shape) {
//        return switch (shape) {
//            case Rectangle(Point topLeft, Point bottomRight) ->
//                    (bottomRight.x() - topLeft.x()) * (bottomRight.y() - topLeft.y());
//
//            case Triangle(Point p1, Point p2, Point p3) -> {
//                // Using the Shoelace formula (Gauss's area formula)
//                double area = 0.5 * Math.abs(
//                        (p1.x() * (p2.y() - p3.y()) +
//                                p2.x() * (p3.y() - p1.y()) +
//                                p3.x() * (p1.y() - p2.y()))
//                );
//                yield area;
//            }
//
//            default -> 0.0;
//        };
//    }
//
//    /**
//     * Sequenced Collections provide methods to work with collections that have
//     * a well-defined encounter order
//     */
//    private static void demonstrateSequencedCollections() {
//        System.out.println("\n----- Sequenced Collections -----");
//
//        // SequencedCollection examples
//        System.out.println("--- SequencedCollection ---");
//        List<String> list = new ArrayList<>(List.of("first", "second", "third", "fourth"));
//
//        System.out.println("Original list: " + list);
//        System.out.println("getFirst(): " + list.getFirst());
//        System.out.println("getLast(): " + list.getLast());
//
//        // Reverse the list
//        List<String> reversed = list.reversed();
//        System.out.println("Reversed list: " + reversed);
//
//        // Add elements at both ends
//        list.addFirst("new first");
//        list.addLast("new last");
//        System.out.println("After adding at both ends: " + list);
//
//        // Remove from ends
//        String removedFirst = list.removeFirst();
//        String removedLast = list.removeLast();
//        System.out.println("Removed first: " + removedFirst);
//        System.out.println("Removed last: " + removedLast);
//        System.out.println("After removals: " + list);
//
//        // SequencedSet examples
//        System.out.println("\n--- SequencedSet ---");
//        LinkedHashSet<String> set = new LinkedHashSet<>(List.of("a", "b", "c", "d"));
//
//        System.out.println("Original set: " + set);
//        System.out.println("getFirst(): " + set.getFirst());
//        System.out.println("getLast(): " + set.getLast());
//
//        // Cannot have duplicates
//        boolean added = set.add("a");
//        System.out.println("Added duplicate 'a'? " + added);
//
//        // Reverse the set
//        Set<String> reversedSet = set.reversed();
//        System.out.println("Reversed set: " + reversedSet);
//
//        // SequencedMap examples
//        System.out.println("\n--- SequencedMap ---");
//        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
//        map.put(1, "one");
//        map.put(2, "two");
//        map.put(3, "three");
//
//        System.out.println("Original map: " + map);
//
//        System.out.println("firstEntry(): " + map.firstEntry());
//        System.out.println("lastEntry(): " + map.lastEntry());
//
//        System.out.println("firstKey(): " + map.firstKey());
//        System.out.println("lastKey(): " + map.lastKey());
//
//        // putFirst and putLast
//        map.putFirst(0, "zero");
//        map.putLast(4, "four");
//        System.out.println("After putting at both ends: " + map);
//
//        // Get a reversed map
//        Map<Integer, String> reversedMap = map.reversed();
//        System.out.println("Reversed map: " + reversedMap);
//
//        // Use pollFirstEntry/pollLastEntry
//        Map.Entry<Integer, String> first = map.pollFirstEntry();
//        Map.Entry<Integer, String> last = map.pollLastEntry();
//        System.out.println("Polled first: " + first);
//        System.out.println("Polled last: " + last);
//        System.out.println("After polling: " + map);
//    }
//
//    /**
//     * String Templates provide a more flexible way to create strings
//     * Note: This is a preview feature in Java 21
//     */
//    private static void demonstrateStringTemplates() {
//        System.out.println("\n----- String Templates (Preview Feature) -----");
//        System.out.println("Note: String Templates require --enable-preview flag in Java 21");
//
//        // Note: The following code would use the actual String Template syntax
//        // with the STR template processor, but since it's a preview feature,
//        // I'll show the concept using traditional formatting methods.
//
//        String name = "Java";
//        int version = 21;
//
//        // Simple variable interpolation
//        // With String Templates, this would be:
//        // String greeting = STR."Hello, \{name}! Welcome to Java \{version}";
//        String greeting = "Hello, " + name + "! Welcome to Java " + version;
//        System.out.println("Simple interpolation example:");
//        System.out.println(greeting);
//
//        // Embedding expressions
//        // With String Templates:
//        // String expressionExample = STR."The answer is \{40 + 2} and version squared is \{version * version}";
//        String expressionExample = "The answer is " + (40 + 2) + " and version squared is " + (version * version);
//        System.out.println("\nExpression example:");
//        System.out.println(expressionExample);
//
//        // Multi-line example
//        // With String Templates:
//        // String html = STR."""
//        //     <html>
//        //       <body>
//        //         <h1>Welcome, \{name}!</h1>
//        //         <p>You are using Java \{version}</p>
//        //       </body>
//        //     </html>
//        //     """;
//        String html = """
//            <html>
//              <body>
//                <h1>Welcome, %s!</h1>
//                <p>You are using Java %d</p>
//              </body>
//            </html>
//            """.formatted(name, version);
//        System.out.println("\nMulti-line example:");
//        System.out.println(html);
//
//        // Formatted values
//        double value = 123.456789;
//        // With String Templates:
//        // String formattedValue = STR."Value with 2 decimal places: \{value%.2f}";
//        String formattedValue = String.format("Value with 2 decimal places: %.2f", value);
//        System.out.println("\nFormatted value example:");
//        System.out.println(formattedValue);
//
//        // Complex example with conditional logic
//        int score = 85;
//        String status = (score >= 70) ? "Pass" : "Fail";
//        // With String Templates:
//        // String result = STR."Test score: \{score}/100 (\{status}) \{score >= 90 ? "Excellent!" : ""}";
//        String result = "Test score: " + score + "/100 (" + status + ") " +
//                (score >= 90 ? "Excellent!" : "");
//        System.out.println("\nComplex example with conditionals:");
//        System.out.println(result);
//
//        // JSON example
//        String user = "Alice";
//        int age = 30;
//        String[] hobbies = {"reading", "hiking", "coding"};
//        // With String Templates:
//        // String json = STR."""
//        //     {
//        //       "user": "\{user}",
//        //       "age": \{age},
//        //       "isAdult": \{age >= 18},
//        //       "hobbies": [
//        //         \{String.join(", ", Arrays.stream(hobbies).map(h -> "\"" + h + "\"").toArray(String[]::new))}
//        //       ]
//        //     }
//        //     """;
//        String hobbiesJson = Arrays.stream(hobbies)
//                .map(h -> "\"" + h + "\"")
//                .collect(java.util.stream.Collectors.joining(", "));
//        String json = """
//            {
//              "user": "%s",
//              "age": %d,
//              "isAdult": %b,
//              "hobbies": [
//                %s
//              ]
//            }
//            """.formatted(user, age, age >= 18, hobbiesJson);
//        System.out.println("\nJSON example:");
//        System.out.println(json);
//    }
//}
//
//
