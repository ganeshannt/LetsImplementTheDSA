package com.practise.java.lambda.withoutlambda;

import com.practise.java.lambda.Hotel;
import com.practise.java.lambda.HotelType;

class FilterFiveStarHotel implements HotelFilteringCondition {
    @Override
    public boolean test(Hotel hotel) {
        return hotel.getHotelType() == HotelType.FIVE_STAR;
    }
}
