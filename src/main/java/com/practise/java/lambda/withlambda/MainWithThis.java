package com.practise.java.lambda.withlambda;

import com.practise.java.lambda.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;

class MainWithThis {

    private int field;

    public static void main(String[] args) {
        MainWithThis mainWithThis = new MainWithThis();
        mainWithThis.field = 10;
        mainWithThis.explainThisContextAndLocalVariable();
        mainWithThis.explainPredicates();
        mainWithThis.explainConsumer();
        mainWithThis.explainSupplier();
        mainWithThis.explainFunction();
    }


    /*
    * Interface Description
        1. BiConsumer<T,U> It represents an operation that accepts two input arguments and returns no result.

        2. Consumer<T> It represents an operation that accepts a single argument and returns no result.

        3. Function<T,R> It represents a function that accepts one argument and returns a result.

        4. Predicate<T> It represents a predicate (boolean-valued function) of one argument.
    * */

    void explainThisContextAndLocalVariable() {
        HotelServiceSingleMethod hotelService = new HotelServiceSingleMethod();
        /*
            1. Primitive local variables are not allowed to change the value inside lambda
            2. We can use this local variable inside lambda, but we should not change the value of it, hence it should be final
            3. If you try to change the value of it, it will give compile time error
         */
        int condition_price = 1000;

        // atomic or other custom final primitive class wrapper can be used inside lambda
        AtomicInteger condition_price1 = new AtomicInteger(1000);
        //final CustomInteger customIntegerWrapper = 100;

        /*
            1. Object type local variables are allowed to change the value inside lambda but reference should not be changed
            2. If we change the reference of the object, then it will give compile time error because Variable used in lambda expression should be final or effectively final
         */
        List<Integer> arrayList = new ArrayList<>();


        Predicate<Hotel> lambdaExp = hotel -> {
            // this keyword inside lambda refers to the current class not the lambda anonymous inner the class
            System.out.println("this.field = " + this.field);

            arrayList.add(10);
            arrayList.add(20);
            arrayList.add(30);
            System.out.println("arrayList = " + arrayList);

            //arrayList = new ArrayList<>(); - this will give compile time error

            /*  below line will be converted like this during runtime, that's why it should be final or should not change the value
             * "return hotel.getPricePerNight() <= 1000;"
             * */
            return hotel.getPricePerNight() <= condition_price;
        };

        /*
        1. The Lambda Restriction (Effectively Final):
            Java lambdas can only access local variables from their surrounding scope if those variables are final or effectively final.
            This means the variable's value (for primitives) or reference (for objects) cannot be changed after initialization within that scope.
            Directly modifying a primitive (like i++) inside a lambda is forbidden because it involves reassigning the variable (i = i + 1), violating this rule.

        2. The Wrapper Solution (Circumventing the Restriction):
            To modify a value from within a lambda, you need to use an object wrapper.
            You declare a final (or effectively final) variable that holds a reference to an object (like an AtomicInteger or a custom wrapper instance).
            The lambda captures this fixed reference. Inside the lambda, you don't change the reference variable; instead, you call methods on the object itself (e.g., atomicCounter.incrementAndGet() or customWrapper.setValue(...)) to modify its internal state.
            This is allowed because the captured variable (the reference) remains unchanged.

        3.AtomicInteger for Concurrency (Thread Safety):
            While a simple custom wrapper object solves the lambda syntax problem, it is not thread-safe by default.
            If multiple threads might execute the lambda and modify the value concurrently, you'll encounter race conditions with a basic wrapper.
            AtomicInteger is specifically designed for this: it not only acts as a wrapper but also provides atomic, thread-safe methods (like incrementAndGet, compareAndSet) using efficient, often lock-free techniques (like CAS).
            You use AtomicInteger when you need both the wrapper mechanism for the lambda and guaranteed thread-safety for concurrent updates.
         */

        Predicate<Hotel> lambdaExp2 = hotel -> {
            condition_price1.set(200);
            //customIntegerWrapper.setValue(200);
            return false;
        };


        hotelService.filterHotels(lambdaExp);
    }

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
