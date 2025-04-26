package com.practise.java.lambda.withlambda;


import com.practise.java.lambda.Hotel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

class LambdaTester {

    public static void main(String[] args) {

        /*
         * Java 8 onwards, we can use Predicate interface instead of custom interface to pass the condition to filter the data.
         * It avoids creating multiple interfaces for different conditions.
         * */

        HotelServiceSingleMethod hotelService = new HotelServiceSingleMethod();
        List<Hotel> hotelList;

        // Here we are not explicitly mentioning any anonymous inner class to provide implementation for new HotelFilteringCondition{}.
        // Using type reference, Predicate (like HotelFilteringCondition) will be used.
        hotelList = hotelService.filterHotels(hotel -> hotel.getPricePerNight() < 20000);
        System.out.println("Using lambda expression, hotels less than 2000 price" + hotelList);

        //lambda expression as a reference
        // type of reference variable will always be the interface
        Predicate<Hotel> lambdaExp = hotel -> hotel.getPricePerNight() <= 1000;
        hotelList = hotelService.filterHotels(lambdaExp);

        System.out.println("Using lambda expression as a reference, hotels less than 1000 price" + hotelList);

        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 5));
        // No return statement and no ; at the end. Here we are passing compare method as parameter to sort method
        Collections.sort(list, (Integer a, Integer b) -> b - a);
        System.out.println(list);
    }
}
