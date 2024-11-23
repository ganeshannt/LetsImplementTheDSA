package com.practise.lld.riderapp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ganeshan Nagarajan
 * @since 23/11/24
 */

@Getter
@Setter
class Ride {

    private static final int PRICE_PER_KM = 20;

    private int id;
    private int origin;
    private int destination;
    private int noOfSeats;
    private RideStatus rideStatus;

    public Ride(int id, int origin, int destination, int noOfSeats, RideStatus rideStatus) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.noOfSeats = noOfSeats;
        this.rideStatus = rideStatus;
    }

    int calculatePrice(boolean isPreferredRider) {
        int price = getDistance() * PRICE_PER_KM;

        if (noOfSeats >= 2) {
            if (isPreferredRider) {
                price *= (int) (noOfSeats * 0.5);
            } else {
                price *= (int) (noOfSeats * 0.75);
            }
        }
        return price;
    }

    int getDistance() {
        return destination - origin;
    }
}
