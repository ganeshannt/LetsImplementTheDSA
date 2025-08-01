package com.practise.java.lambda.withlambda;

import com.practise.java.lambda.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

class MainWithThis {

    private int field;

    public static void main(String[] args) {
        MainWithThis mainWithThis = new MainWithThis();
        mainWithThis.field = 10;
        mainWithThis.explainThisContextAndLocalVariable();
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
}
