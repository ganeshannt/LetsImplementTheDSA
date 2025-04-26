package com.practise.lld.ticketbooking.provider;

import com.practise.lld.ticketbooking.model.Seat;
import com.practise.lld.ticketbooking.model.Show;

import java.util.List;

public interface SeatLockProvider {

    void lockSeat(Show show, List<Seat> seats, String lockedBy);

    void unLockSeat(Show show, List<Seat> seats, String lockedBy);

    boolean validateLock(Show show, Seat seat, String lockedBy);

    List<Seat> getLockedSeats(Show show);
}
