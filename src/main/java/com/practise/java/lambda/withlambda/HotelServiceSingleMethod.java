package com.practise.java.lambda.withlambda;


import com.practise.java.lambda.Hotel;
import com.practise.java.lambda.HotelType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class HotelServiceSingleMethod {
    List<Hotel> hotelList = new ArrayList<>();

    public HotelServiceSingleMethod() {
        hotelList.add(new Hotel(2000, 2, HotelType.THREE_STAR));
        hotelList.add(new Hotel(1000, 1, HotelType.THREE_STAR));
        hotelList.add(new Hotel(10000, 4, HotelType.FOUR_STAR));
        hotelList.add(new Hotel(20000, 5, HotelType.FIVE_STAR));
    }

    //generic method
    public List<Hotel> filterHotels(Predicate<Hotel> hotelFilteringCondition) {            //user is going to pass this filter condition
        List<Hotel> filteredHotels = new ArrayList<>();

        for (Hotel hotel : hotelList) {
            if (hotelFilteringCondition.test(hotel)) {
                filteredHotels.add(hotel);
            }
        }
        return filteredHotels;
    }
}
