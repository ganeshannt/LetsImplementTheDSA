package com.practise.java.lambda.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

class StreamClasses {

    public static void main(String[] args) {
        StreamClasses mainWithThis = new StreamClasses();
        mainWithThis.explainPredicates();
        mainWithThis.explainConsumer();
        mainWithThis.explainSupplier();
        mainWithThis.explainFunction();
    }

    /*
     * Java Functional Interfaces Commonly Used with Streams
     *
     * 1. Predicate<T>
     *      Represents a boolean-valued function of one argument.
     *      Example: x -> x > 10
     *
     * 2. BiPredicate<T, U>
     *      Represents a boolean-valued function of two arguments.
     *      Example: (a, b) -> a.equalsIgnoreCase(b)
     *
     * 3. Function<T, R>
     *      Represents a function that accepts one argument and produces a result.
     *      Example: x -> x.toString()
     *
     * 4. BiFunction<T, U, R>
     *      Represents a function that accepts two arguments and produces a result.
     *      Example: (a, b) -> a + b
     *
     * 5. Consumer<T>
     *      Represents an operation that accepts a single argument and returns no result (side-effect).
     *      Example: x -> System.out.println(x)
     *
     * 6. BiConsumer<T, U>
     *      Represents an operation that accepts two input arguments and returns no result.
     *      Example: (k, v) -> System.out.println(k + ":" + v)
     *
     * 7. Supplier<T>
     *      Represents a supplier of results; produces a result without any input.
     *      Example: () -> new Random().nextInt()
     *
     * 8. BooleanSupplier
     *      Represents a supplier of boolean-valued results; produces a boolean value without input.
     *      Example: () -> Math.random() > 0.5
     *
     * 9. UnaryOperator<T>
     *      Represents an operation on a single operand that produces a result of the same type.
     *      Example: x -> x * x
     *      (Specialization of Function<T, T>)
     *
     * 10. BinaryOperator<T>
     *      Represents an operation upon two operands of the same type, producing a result of the same type.
     *      Example: (a, b) -> a + b
     *      (Specialization of BiFunction<T, T, T>)
     */

// Example usages mapped to streams:
    /*
     * Predicate<T>         -> filter(x -> x > 10)
     * BiPredicate<T, U>    -> filter((s, t) -> s.startsWith(t))
     * Function<T, R>       -> map(x -> x.length())
     * BiFunction<T, U, R>  -> reduce((a, b) -> a + b)
     * Consumer<T>          -> forEach(x -> ...)
     * BiConsumer<T, U>     -> map.forEach((k, v) -> ...)
     * Supplier<T>          -> Optional.orElseGet(() -> ...)
     * BooleanSupplier      -> Stream.generate(() -> boolean)
     * UnaryOperator<T>     -> map(x -> operation on x of same type)
     * BinaryOperator<T>    -> reduce((a, b) -> combine a and b of same type)
     */


    void explainPredicates() {

        System.out.println("Predicate code block");

        // Predicate is a functional interface that takes one argument and returns a boolean value.
        // It has 'test' method which takes one argument and returns a boolean value.
        // It is used when you want to test some condition and return a boolean value.

        Predicate<Integer> divisibleBy2 = number -> number % 2 == 0;
        // we can use more specific type like IntPredicate, LongPredicate, DoublePredicate
        Predicate<Integer> divisibleBy3 = number -> number % 3 == 0;

        // combining predicates
        Predicate<Integer> divisibleBy6 = divisibleBy2.and(divisibleBy3);
        System.out.println("divisibleBy6.test(6) = " + divisibleBy6.test(6));

        //negate
        System.out.println("divisibleBy6.negate().test(6) = " + divisibleBy6.negate().test(6));
    }

    void explainConsumer() {

        System.out.println("Consumer code block");

        // Consumer is a functional interface that takes one argument and consume it but returns nothing.
        // It has 'accept' method which takes one argument and returns nothing.
        // It is used when you want to do some operations on the argument but don't want to return anything.

        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));

        Consumer<Integer> consumer = number -> System.out.println(number);
        // since consumer takes one argument.
        // both are same: "Consumer<Integer> consumer = System.out::println;"
        // we can use more specific type like IntConsumer, LongConsumer, DoubleConsumer
        list.forEach(consumer);

        // Chaining consumers
        //consumer 1: 53 -  When accept(5) is called: 5 + 3 = 8 but we used string concatenation so it will be 5 + "3" = 53
        //consumer 2: 25 -  When accept(5) is called: 5 * 5 = 25. Multiply is not a string operation, so it will be 25
        Consumer<Integer> consumer1 = number -> System.out.println("consumer 1: " + number + 3);
        Consumer<Integer> consumer2 = number -> System.out.println("consumer 2: " + number * number);

        // accept method will be called on consumer1 and then consumer2
        consumer1.andThen(consumer2).accept(5);

    }

    void explainSupplier() {

        System.out.println("Supplier code block");

        // Supplier is a functional interface that takes no argument and returns a value.
        // It has 'get' method which takes no argument and returns a value.
        // It is used when you want to return some value without taking any argument.

        Supplier<Double> supplier = () -> Math.random();
        System.out.println("supplier.get() = " + supplier.get());
    }

    void explainFunction() {
        // Function is a functional interface that takes one argument and returns a value.
        // It has 'apply' method which takes one argument and returns a value.
        // It is used when you want to apply some operation on the argument and return the result.

        System.out.println("Function code block");

        Function<String, Integer> strToLenMap = string -> string.length();
        int strLength = strToLenMap.apply("Hello world!");
        System.out.println(strLength);


        Function<Double, Double> square = a -> a * a;
        Function<Double, Double> plusTwo = a -> a + 2;
        IntUnaryOperator plusOne = a -> a + 1;

        System.out.println(square.apply(10.0)); // 100.0
        System.out.println(plusOne.applyAsInt(10)); // 11

        System.out.println(square.andThen(plusTwo).apply(10.0)); // 102.0

    }

    void explainReduce() {
        // Reduce is a terminal operation that takes a collection and reduces it to a single value.
        // It takes a binary operator and an identity value.
        // It is used when you want to reduce a collection to a single value.

        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        int sum = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println("sum = " + sum);
    }

    void explainMap() {
        // Map is an intermediate operation that takes a collection and transforms it to another collection.
        // It takes a function and applies it to each element of the collection.
        // It is used when you want to transform a collection to another collection.

        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        List<Integer> squareList = list.stream().map(number -> number * number).toList();
        System.out.println("squareList = " + squareList);
    }

    void explainBinaryOperator() {
        // BinaryOperator is a functional interface that takes two arguments and returns a value.
        // It has 'apply' method which takes two arguments and returns a value.
        // It is used when you want to apply some operation on the two arguments and return the result.

        BinaryOperator<Integer> sum = (a, b) -> a + b;
        System.out.println("sum.apply(10, 20) = " + sum.apply(10, 20));
    }

    void explainFlatMap() {
        /*
         * At its core, flatMap does two things in one operation:
         * It maps each element in a stream to another stream (similar to map)
         * It then "flattens" all those resulting streams into a single stream
         * */
        // FlatMap is an intermediate operation that takes a collection of collections and flattens it to a single collection.
        // It takes a function and applies it to each element of the collection.
        // It is used when you want to flatten a collection of collections to a single collection.

        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>(List.of(1, 2, 3)));
        list.add(new ArrayList<>(List.of(4, 5, 6)));
        list.add(new ArrayList<>(List.of(7, 8, 9)));

        List<Integer> flatList = list.stream().flatMap(List::stream).toList();
        System.out.println("flatList = " + flatList);
    }
}
