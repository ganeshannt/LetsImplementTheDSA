package com.practise.java.lambda.withlambda;

import com.practise.java.lambda.Hotel;


/*
 * Predicates are functional interfaces that can be used as the assignment target for a lambda expression from the Java 8 release.
 * Predicates replaced the boolean test method in the HotelFilteringCondition interface.
 * */

@FunctionalInterface
interface HotelFilteringCondition {
    boolean test(Hotel hotel);
}
