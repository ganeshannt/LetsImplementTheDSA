package com.practise.lld.ticketbooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;


@AllArgsConstructor
@Getter
public class SeatLock {
    private final Seat seat;
    private final Show show;
    private final ZonedDateTime lockTime;
    private final String lockedBy;
    private final Integer lockTimeout;

    public boolean isLockExpired() {
        return ZonedDateTime.now().isAfter(lockTime.plusSeconds(lockTimeout));
    }
}
