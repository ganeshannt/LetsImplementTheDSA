package com.practise.lld.ticketbooking.model;


import com.practise.lld.ticketbooking.exception.InvalidStateException;
import lombok.Getter;

import java.util.List;

@Getter
public class Booking {
    private final String id;
    private final Show show;
    private final String user;
    private final List<Seat> seats;
    private BookingStatus status;

    public Booking(String id, Show show, String user, List<Seat> seats) {
        this.id = id;
        this.show = show;
        this.user = user;
        this.seats = seats;
        this.status = BookingStatus.CREATED;
    }


    public boolean isBookingConfirmed() {
        return this.status == BookingStatus.CONFIRMED;
    }

    public void confirm() {
        if (this.status != BookingStatus.CREATED) {
            throw new InvalidStateException("Booking is in invalid state to confirm");
        }
        this.status = BookingStatus.CONFIRMED;
    }

    public void expire() {
        if (this.status != BookingStatus.CREATED) {
            throw new InvalidStateException("Booking is in invalid state to expire");
        }
        this.status = BookingStatus.EXPIRED;
    }
}
