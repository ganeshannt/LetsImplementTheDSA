package com.practise.java.lambda.withoutlambda;

import com.practise.java.lambda.Hotel;

class FilterHotelLessThan20000 implements HotelFilteringCondition {
    @Override
    public boolean test(Hotel hotel) {
        return hotel.getPricePerNight() < 20000;
    }
}
