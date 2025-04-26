package com.practise.java.lambda;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hotel {
    private int pricePerNight;
    private int rating;
    private HotelType hotelType;
}
