package com.practise.lld.riderapp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ganeshan Nagarajan
 * @since 23/11/24
 */

@Getter
@Setter
class Driver extends Person {
    Ride currentRide;

    Driver(String name) {
        super(name);
    }
}
