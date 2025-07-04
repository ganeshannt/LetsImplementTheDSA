package com.practise.java.lambda.withoutlambda;

import com.practise.java.lambda.Hotel;

import java.util.List;

/*
here we are trying to pass function as method argument without a lambda.
it will show you the period where lambda was not introduced in java and how we used to pass function as method argument.
demonstrates the need of lambda to avoid duplicates and boilerplate code
 */
class WithoutLambdaTester {

    public static void main(String[] args) {

        HotelService hotelServiceNormal = new HotelService();
        List<Hotel> hotelList;

        /* used normal service class method filterHotelLessThan to do filtering. what if we want to filter by 5-star hotel
        we need to write another method in service class to filter by 5-star hotel.
         */
        List<Hotel> filterHotelLessThan = hotelServiceNormal.filterHotelLessThan(20000);
        List<Hotel> filterFiveStarHotel = hotelServiceNormal.filterFiveStarHotel();
        System.out.println("Using normal service class, hotels less than 2000 price" + filterHotelLessThan);
        System.out.println("Using normal service class, five star hotels" + filterFiveStarHotel);

        /*
        Using single method service class.
        It has a generic method where we can pass the different implementation of HotelFilteringCondition so here we can avoid code duplication
        it is more flexible and reusable
         */
        HotelServiceSingleMethod hotelService = new HotelServiceSingleMethod();

        //inside filterHotels, user needs to pass the required condition and here the condition FilterHotelLessThan20000 that provides the condition by implementing HotelFilteringCondition
        hotelList = hotelService.filterHotels(new FilterHotelLessThan20000());
        System.out.println("Using single method service class, hotels less than 2000 price" + hotelList);

        // 5 five-star hotels filter.
        // The Problem here is if you want another condition,
        // you have
        // to create another class that implements HotelFilteringCondition
        // so each time you have to create a new class
        List<Hotel> fiveStarHotelList = hotelService.filterHotels(new FilterFiveStarHotel());
        System.out.println("Using single method service class, five star hotels" + fiveStarHotelList);

        //Here we are creating anonymous inner class to pass the HotelFilteringCondition instead of creating a new class.
        // This is very similar to lambda but with more boilerplate code.
        // Lambda will create this anonymous inner class (to implement HotelFilteringCondition. like FilterHotelLessThan20000) under the hood, so you can directly provide the implementation of interface method.
        hotelList = hotelService.filterHotels(new HotelFilteringCondition() {
            @Override
            public boolean test(Hotel hotel) {
                return hotel.getPricePerNight() < 20000;
            }
        });

        System.out.println("Using anonymous inner class in single method service, hotels less than 2000 price" + hotelList);
    }
}
