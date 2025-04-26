package com.practise.java.versions;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This class demonstrates the key features introduced in Java 17 LTS.
 * To compile and run:
 * javac Java17Features.java
 * java Java17Features
 * <p>
 * <p>
 * <p>
 * Java 17 Features
 * Records: Concise classes for data carriers
 * Pattern Matching for instanceof: Simplified casting after instanceof
 * Sealed Classes: Restricted class hierarchies
 * Text Blocks: Multi-line string literals
 * Switch Expressions: More powerful switch statements
 * Helpful NullPointerExceptions: Better error messages
 * Compact Number Formatting: Improved number representation
 */
public class Java17Features {

    public static void main(String[] args) {
        System.out.println("========== JAVA 17 FEATURES SHOWCASE ==========");

        demonstrateRecords();
        demonstratePatternMatchingForInstanceof();
        demonstrateSealedClasses();
        demonstrateTextBlocks();
        demonstrateSwitchExpressions();
        demonstrateHelpfulNullPointerExceptions();
        demonstrateCompactNumberFormatting();
    }

    /**
     * Records provide a concise syntax for creating immutable data classes
     */
    private static void demonstrateRecords() {
        System.out.println("\n----- Records -----");

        // Create a simple record
        Person person = new Person("John Doe", 30);
        System.out.println("Person: " + person);
        System.out.println("Name: " + person.name());
        System.out.println("Age: " + person.age());

        // Records have automatic equals, hashCode, and toString
        Person person2 = new Person("John Doe", 30);
        Person person3 = new Person("Jane Doe", 28);

        System.out.println("person equals person2? " + person.equals(person2));
        System.out.println("person equals person3? " + person.equals(person3));
        System.out.println("person hashCode: " + person.hashCode());

        // Record with custom constructor for validation
        try {
            Employee employee = new Employee("Alice Smith", -5, "IT");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        }

        Employee validEmployee = new Employee("Alice Smith", 42, "IT");
        System.out.println("Valid employee: " + validEmployee);
        System.out.println("Department: " + validEmployee.department());
        System.out.println("Is senior? " + validEmployee.isSenior());

        // Record with custom methods
        Point p1 = new Point(3, 4);
        Point p2 = new Point(0, 0);
        System.out.println("Distance from " + p1 + " to " + p2 + ": " + p1.distanceTo(p2));

        // Nested records
        Address address = new Address("123 Main St", "Springfield", "IL", "62701");
        Contact contact = new Contact("Bob Johnson", address, "bob@example.com");
        System.out.println("Contact: " + contact);
        System.out.println("City: " + contact.address().city());

        // Records with generics
        Pair<String, Integer> pair = new Pair<>("answer", 42);
        System.out.println("Pair: " + pair);

        // Local records (defined within a method)
        record LocalScore(String playerName, int value) {
        }

        LocalScore score = new LocalScore("Player1", 100);
        System.out.println("Local record: " + score);
    }

    /**
     * Pattern matching for instanceof simplifies the common pattern of casting after an instanceof check
     */
    private static void demonstratePatternMatchingForInstanceof() {
        System.out.println("\n----- Pattern Matching for instanceof -----");

        // Create some shapes
        Object circle = new Circle(5.0);
        Object rectangle = new Rectangle(4.0, 6.0);
        Object string = "Not a shape";

        // Before pattern matching
        System.out.println("---- Old style (with casting) ----");
        printShapeInfoOldStyle(circle);
        printShapeInfoOldStyle(rectangle);
        printShapeInfoOldStyle(string);

        // With pattern matching
        System.out.println("\n---- New style (with pattern matching) ----");
        printShapeInfo(circle);
        printShapeInfo(rectangle);
        printShapeInfo(string);

        // Combining with logical operators
        System.out.println("\n---- Pattern matching with logical operators ----");
        printShapeInfoWithCondition(circle);
        printShapeInfoWithCondition(rectangle);
        printShapeInfoWithCondition(new Circle(2.0));
    }

    // Traditional approach with explicit casting
    private static void printShapeInfoOldStyle(Object obj) {
        if (obj instanceof Circle) {
            Circle circle = (Circle) obj;
            System.out.println("Circle with radius " + circle.radius +
                    ", area: " + String.format("%.2f", circle.area()));
        } else if (obj instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) obj;
            System.out.println("Rectangle with width " + rectangle.width +
                    " and height " + rectangle.height +
                    ", area: " + String.format("%.2f", rectangle.area()));
        } else {
            System.out.println("Not a known shape: " + obj);
        }
    }

    // New approach with pattern matching
    private static void printShapeInfo(Object obj) {
        if (obj instanceof Circle circle) {
            System.out.println("Circle with radius " + circle.radius +
                    ", area: " + String.format("%.2f", circle.area()));
        } else if (obj instanceof Rectangle rectangle) {
            System.out.println("Rectangle with width " + rectangle.width +
                    " and height " + rectangle.height +
                    ", area: " + String.format("%.2f", rectangle.area()));
        } else {
            System.out.println("Not a known shape: " + obj);
        }
    }

    // Pattern matching with logical operators
    private static void printShapeInfoWithCondition(Object obj) {
        if (obj instanceof Circle circle && circle.radius > 3) {
            System.out.println("Large circle with radius " + circle.radius);
        } else if (obj instanceof Rectangle rectangle && rectangle.width > rectangle.height) {
            System.out.println("Wide rectangle with width " + rectangle.width);
        } else if (obj instanceof Shape shape) {
            System.out.println("Other shape with area: " + String.format("%.2f", shape.area()));
        } else {
            System.out.println("Not a shape: " + obj);
        }
    }

    /**
     * Sealed classes restrict which other classes can extend or implement them
     */
    private static void demonstrateSealedClasses() {
        System.out.println("\n----- Sealed Classes -----");

        // Create instances of permitted subclasses
        Vehicle car = new Car(4, "Tesla Model 3");
        Vehicle truck = new Truck(18, 10.5);
        Vehicle motorcycle = new Motorcycle("Harley-Davidson", 750);

        // Process them
        printVehicleDetails(car);
        printVehicleDetails(truck);
        printVehicleDetails(motorcycle);

        // Using with switch expressions
        System.out.println("\nVehicle emissions classifications:");
        System.out.println(car + ": " + getEmissionsCategory(car));
        System.out.println(truck + ": " + getEmissionsCategory(truck));
        System.out.println(motorcycle + ": " + getEmissionsCategory(motorcycle));
    }

    private static void printVehicleDetails(Vehicle vehicle) {
        System.out.println(vehicle.getDescription());
    }

    private static String getEmissionsCategory(Vehicle vehicle) {
        if (vehicle instanceof Car car) {
            if (car.getModel().startsWith("Tesla")) {
                return "Zero Emissions";
            } else {
                return "Standard Emissions";
            }
        } else if (vehicle instanceof Truck truck) {
            if (truck.getWheels() > 10) {
                return "Heavy Duty Emissions";
            } else {
                return "Medium Duty Emissions";
            }
        } else if (vehicle instanceof Motorcycle motorcycle) {
            if (motorcycle.getEngineCC() > 500) {
                return "Performance Motorcycle Emissions";
            } else {
                return "Standard Motorcycle Emissions";
            }
        } else {
            // Handle the case where the vehicle type is not recognized
            return "Unknown Emissions Category";
        }
    }

    /**
     * Text blocks provide a way to create multi-line string literals with less escape sequences
     */
    private static void demonstrateTextBlocks() {
        System.out.println("\n----- Text Blocks -----");

        // Before text blocks
        String oldWayJson = "{\n" +
                "  \"name\": \"John Doe\",\n" +
                "  \"age\": 30,\n" +
                "  \"address\": {\n" +
                "    \"street\": \"123 Main St\",\n" +
                "    \"city\": \"Anytown\"\n" +
                "  }\n" +
                "}";

        System.out.println("JSON (old way):");
        System.out.println(oldWayJson);

        // With text blocks
        String jsonTextBlock = """
                {
                  "name": "John Doe",
                  "age": 30,
                  "address": {
                    "street": "123 Main St",
                    "city": "Anytown"
                  }
                }
                """;

        System.out.println("\nJSON (text block):");
        System.out.println(jsonTextBlock);

        // HTML example
        String html = """
                <html>
                  <body>
                    <h1>Hello, World!</h1>
                    <p>This is a paragraph.</p>
                    <ul>
                      <li>Item 1</li>
                      <li>Item 2</li>
                      <li>Item 3</li>
                    </ul>
                  </body>
                </html>
                """;

        System.out.println("\nHTML example:");
        System.out.println(html);

        // SQL example
        String sql = """
                SELECT e.id, e.name, e.salary, d.name as department
                FROM employees e
                JOIN departments d ON e.dept_id = d.id
                WHERE e.salary > 50000
                  AND d.location = 'HQ'
                ORDER BY e.salary DESC
                """;

        System.out.println("\nSQL example:");
        System.out.println(sql);

        // Text block with variable interpolation
        String name = "Alice";
        int age = 30;

        String greeting = """
                Hello, %s!
                You are %d years old.
                Welcome to Java 17.
                """.formatted(name, age);

        System.out.println("\nFormatted text block:");
        System.out.println(greeting);

        // Text block with escape sequences
        String escaped = """
                This is a line.
                This is another line.
                This line has a tab:\ttabbed content
                This line has a unicode symbol: \u2022 bullet
                """;

        System.out.println("\nText block with escapes:");
        System.out.println(escaped);

        // Controlling new lines and spaces
        String compact = """
                First line\
                Still first line
                Second line""";

        System.out.println("\nControlling new lines:");
        System.out.println(compact);
    }

    /**
     * Switch expressions allow switch to be used as an expression and support lambda-like case labels
     */
    private static void demonstrateSwitchExpressions() {
        System.out.println("\n----- Switch Expressions -----");

        // Old style switch statement
        DayOfWeek day = DayOfWeek.WEDNESDAY;
        String typeOfDayOld;

        switch (day) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                typeOfDayOld = "Weekday";
                break;
            case SATURDAY:
            case SUNDAY:
                typeOfDayOld = "Weekend";
                break;
            default:
                typeOfDayOld = "Unknown";
                break;
        }

        System.out.println("Old style: " + day + " is a " + typeOfDayOld);

        // New switch expression with arrow syntax
        String typeOfDayNew = switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
            case SATURDAY, SUNDAY -> "Weekend";
        };

        System.out.println("New style: " + day + " is a " + typeOfDayNew);

        // Switch expression with blocks and yield
        String result = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> {
                System.out.println("Processing " + day);
                yield "Start or end of work week";
            }
            case TUESDAY, THURSDAY -> {
                System.out.println("Processing " + day);
                yield "Midweek";
            }
            case SATURDAY -> "Weekend";
            case WEDNESDAY -> {
                yield "Hump day";
            }
        };

        System.out.println("Result: " + result);

        // Switch with enums
        Month month = Month.AUGUST;
        String season = switch (month) {
            case DECEMBER, JANUARY, FEBRUARY -> "Winter";
            case MARCH, APRIL, MAY -> "Spring";
            case JUNE, JULY, AUGUST -> "Summer";
            case SEPTEMBER, OCTOBER, NOVEMBER -> "Fall";
        };

        System.out.println(month + " is in " + season);

        // Switch with String
        String fruit = "APPLE";
        String fruitType = switch (fruit.toLowerCase()) {
            case "apple", "pear" -> "Pome fruit";
            case "cherry", "plum", "peach" -> "Stone fruit";
            case "blueberry", "raspberry" -> "Berry";
            default -> {
                System.out.println("Unknown fruit: " + fruit);
                yield "Unknown fruit type";
            }
        };

        System.out.println(fruit + " is a " + fruitType);

        // Switch with int
        int number = new Random().nextInt(10);
        String description = switch (number) {
            case 0 -> "zero";
            case 1, 3, 5, 7, 9 -> "odd";
            case 2, 4, 6, 8 -> "even";
            default -> "out of range";
        };

        System.out.println(number + " is " + description);
    }

    /**
     * Java 17 includes more helpful NullPointerException messages
     */
    private static void demonstrateHelpfulNullPointerExceptions() {
        System.out.println("\n----- Helpful NullPointerExceptions -----");
        System.out.println("(Note: This feature is about improved error messages at runtime)");

        try {
            // Setup some objects
            Person person = new Person("Alice", 30);
            Address address = null;
            Contact contact = new Contact(person.name(), address, "alice@example.com");

            // This will throw NPE with more detailed message
            System.out.println("City: " + contact.address().city());
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: " + e.getMessage());
            System.out.println("In Java 17, the message now indicates which part was null.");
            System.out.println("For example, it would say 'Cannot invoke \"Address.city()\" " +
                    "because \"contact.address()\" is null'");
        }
    }

    /**
     * Java 17 includes enhanced pseudo-random number generators and other utility improvements
     */
    private static void demonstrateCompactNumberFormatting() {
        System.out.println("\n----- Compact Number Formatting -----");

        // Improved Random number generation
        Random random = new Random();
        System.out.println("Random int: " + random.nextInt());
        System.out.println("Random double: " + random.nextDouble());

        // Arrays utility enhancements
        int[] numbers = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println("Original array: " + Arrays.toString(numbers));

        // Use new Arrays.toList and mismatch methods
        List<Integer> numbersList = Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("List from array: " + numbersList);

        int[] numbers2 = {3, 1, 4, 1, 5, 9, 2, 7};
        System.out.println("Second array: " + Arrays.toString(numbers2));
        int mismatchIndex = Arrays.mismatch(numbers, numbers2);
        System.out.println("Arrays differ at index: " + mismatchIndex);
    }

    // Simple record - no need for explicit getters, equals, hashCode, toString
    record Person(String name, int age) {
    }

    // Record with validation in compact constructor
    record Employee(String name, int age, String department) {
        // Compact constructor for validation
        public Employee {
            Objects.requireNonNull(name, "Name cannot be null");
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
        }

        // Custom methods can be added
        public boolean isSenior() {
            return age >= 40;
        }
    }

    // Record with custom methods
    record Point(double x, double y) {
        public double distanceTo(Point other) {
            double dx = this.x - other.x;
            double dy = this.y - other.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }

    // Nested records
    record Address(String street, String city, String state, String zipCode) {
    }

    record Contact(String name, Address address, String email) {
    }

    // Generic record
    record Pair<T, U>(T first, U second) {
    }

    // Classes for the instanceof examples
    static abstract class Shape {
        abstract double area();
    }

    static class Circle extends Shape {
        final double radius;

        Circle(double radius) {
            this.radius = radius;
        }

        @Override
        double area() {
            return Math.PI * radius * radius;
        }

        @Override
        public String toString() {
            return "Circle(radius=" + radius + ")";
        }
    }

    static class Rectangle extends Shape {
        final double width;
        final double height;

        Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        double area() {
            return width * height;
        }

        @Override
        public String toString() {
            return "Rectangle(width=" + width + ", height=" + height + ")";
        }
    }

    // Sealed class hierarchy
    sealed static abstract class Vehicle permits Car, Truck, Motorcycle {
        abstract String getDescription();
    }

    // First permitted subclass (final)
    final static class Car extends Vehicle {
        private final int seats;
        private final String model;

        Car(int seats, String model) {
            this.seats = seats;
            this.model = model;
        }

        public int getSeats() {
            return seats;
        }

        public String getModel() {
            return model;
        }

        @Override
        String getDescription() {
            return "Car: " + model + " with " + seats + " seats";
        }

        @Override
        public String toString() {
            return "Car(" + model + ")";
        }
    }

    // Second permitted subclass (final)
    final static class Truck extends Vehicle {
        private final int wheels;
        private final double capacity;

        Truck(int wheels, double capacity) {
            this.wheels = wheels;
            this.capacity = capacity;
        }

        public int getWheels() {
            return wheels;
        }

        public double getCapacity() {
            return capacity;
        }

        @Override
        String getDescription() {
            return "Truck with " + wheels + " wheels and " +
                    capacity + " tons capacity";
        }

        @Override
        public String toString() {
            return "Truck(" + wheels + " wheels)";
        }
    }

    // Third permitted subclass (final)
    final static class Motorcycle extends Vehicle {
        private final String brand;
        private final int engineCC;

        Motorcycle(String brand, int engineCC) {
            this.brand = brand;
            this.engineCC = engineCC;
        }

        public String getBrand() {
            return brand;
        }

        public int getEngineCC() {
            return engineCC;
        }

        @Override
        String getDescription() {
            return "Motorcycle: " + brand + " with " +
                    engineCC + "cc engine";
        }

        @Override
        public String toString() {
            return "Motorcycle(" + brand + ")";
        }
    }
}
