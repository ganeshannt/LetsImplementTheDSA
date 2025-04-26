package com.practise.java.lambda.withoutlambda;

import com.practise.java.lambda.Hotel;
import com.practise.java.lambda.HotelType;

import java.util.ArrayList;
import java.util.List;

class HotelServiceSingleMethod {
    List<Hotel> hotelList = new ArrayList<>();

    public HotelServiceSingleMethod() {
        hotelList.add(new Hotel(2000, 2, HotelType.THREE_STAR));
        hotelList.add(new Hotel(1000, 1, HotelType.THREE_STAR));
        hotelList.add(new Hotel(10000, 4, HotelType.FOUR_STAR));
        hotelList.add(new Hotel(20000, 5, HotelType.FIVE_STAR));
    }

    // we can pass any condition to filter the hotels
    public List<Hotel> filterHotels(HotelFilteringCondition hotelFilteringCondition) {
        List<Hotel> filteredHotels = new ArrayList<>();

        for (Hotel hotel : hotelList) {
            if (hotelFilteringCondition.test(hotel)) {
                filteredHotels.add(hotel);
            }
        }
        return filteredHotels;
    }
}
