package com.practise.java.lambda.withoutlambda;

import com.practise.java.lambda.Hotel;


/*
 * Java 8 has introduced a new annotation @FunctionalInterface to avoid accidental addition of abstract methods in the functional interfaces.It’s optional but good practice to use it.
 * Since the below interface did not annotate with @FunctionalInterface, it will not throw any compilation error if you add more than one abstract method.
 * */

interface HotelFilteringCondition {
    boolean test(Hotel hotel);
}

/*
if you add more than one abstract method in the functional interface, it will throw a compilation error.
@FunctionalInterface
interface HotelFilteringCondition {
    boolean test(Hotel hotel);
}
 */

/*
    * The static method can be defined in the functional interface.
    * Is static final variable accessible in implementing class? - Yes, it is accessible.
    * Default, static methods and static final variables are allowed in functional interface.

@FunctionalInterface
interface HotelFilteringCondition1 {

    Static final String NAME = "static final allowed";

    Boolean test(Hotel hotel);

    default boolean test1(Hotel hotel) {
        return false;
    }

    Static boolean test2(Hotel hotel) {
        return false;
    }
}

Class FunctionalInterface implements HotelFilteringCondition1 {
    @Override
    public boolean test(Hotel hotel) {
        NAME.equals("static final allowed");
        return hotel.getPricePerNight() < 20000;
    }
}
 */
