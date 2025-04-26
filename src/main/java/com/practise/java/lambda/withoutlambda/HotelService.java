package com.practise.java.lambda.withoutlambda;

import com.practise.java.lambda.Hotel;
import com.practise.java.lambda.HotelType;

import java.util.ArrayList;
import java.util.List;

class HotelService {
    List<Hotel> hotelList = new ArrayList<>();

    public HotelService() {
        hotelList.add(new Hotel(2000, 2, HotelType.THREE_STAR));
        hotelList.add(new Hotel(1000, 1, HotelType.THREE_STAR));
        hotelList.add(new Hotel(10000, 4, HotelType.FOUR_STAR));
        hotelList.add(new Hotel(20000, 5, HotelType.FIVE_STAR));
    }

    /*
    for each filtering condition, we have to write a separate method.
    if carefully noticed here, if loop is duplicated and the only change is filter condition.
    we came up with HotelFilteringCondition interface to provide different implementation of condition and pass it to filter hotels.
     */
    public List<Hotel> filterHotelLessThan(int price) {
        List<Hotel> filteredHotels = new ArrayList<>();

        for (Hotel hotels : hotelList) {
            if (isHotelLessThan(price, hotels)) {
                filteredHotels.add(hotels);
            }
        }
        return filteredHotels;
    }

    private boolean isHotelLessThan(int price, Hotel hotel) {
        return hotel.getPricePerNight() < price;
    }

    //method to retrieve a hotel with star 5
    public List<Hotel> filterFiveStarHotel() {
        List<Hotel> filteredHotels = new ArrayList<>();

        for (Hotel hotels : hotelList) {
            if (isHotel5star(hotels)) {
                filteredHotels.add(hotels);
            }
        }
        return filteredHotels;
    }

    private boolean isHotel5star(Hotel hotels) {
        return hotels.getHotelType() == HotelType.FIVE_STAR;
    }
}
