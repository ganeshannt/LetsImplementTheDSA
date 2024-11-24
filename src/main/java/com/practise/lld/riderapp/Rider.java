package com.practise.lld.riderapp;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Ganeshan Nagarajan
 * @since 23/11/24
 */

@Setter
@Getter
class Rider extends Person {

    private final Logger logger = Logger.getLogger(this.getName());

    private Ride currentRide;

    private Map<Integer, Ride> completedRideMap = new HashMap<>();

    public Rider(String name) {
        super(name);
    }

    void createRide(int id, int origin, int destination, int noOfSeats) {
        if (destination < origin) {
            logger.info("wrong origin or destination ... ");
        } else {
            currentRide = new Ride(id, origin, destination, noOfSeats, RideStatus.CREATED);
        }
    }

    void withdrawRide(int id) {
        if (currentRide.getId() != id) {
            logger.info("Current ride is different ...");
        }

        if (currentRide.getRideStatus() == RideStatus.COMPLETED) {
            logger.info("Ride already completed");
        }
        this.currentRide.setRideStatus(RideStatus.WITHDRAWN);
    }

    void updateRide(int id, int origin, int destination, int noOfSeats) {

        if (currentRide.getRideStatus() == RideStatus.COMPLETED) {
            logger.info("Ride already completed");
        } else if (currentRide.getRideStatus() == RideStatus.WITHDRAWN) {
            logger.info("Ride already withdrawn");
        } else {
            createRide(id, origin, destination, noOfSeats);
        }
    }

    int closeRide() {
        if (currentRide.getRideStatus() != RideStatus.CREATED) {
            logger.info("Ride is not available to close");
        }
        currentRide.setRideStatus(RideStatus.COMPLETED);
        completedRideMap.put(currentRide.getId(), currentRide);
        return currentRide.calculatePrice(completedRideMap.size() > 10);
    }
}
