package com.practise.lld.ticketbooking.service;

import com.practise.lld.ticketbooking.model.Booking;
import com.practise.lld.ticketbooking.provider.SeatLockProvider;

import java.util.HashMap;
import java.util.Map;

public class PaymentService {

    private final Map<Booking, Integer> bookingFailureMap;
    private final Integer allowedRetries;
    private final SeatLockProvider seatLockProvider;


    public PaymentService(Integer allowedRetries, SeatLockProvider seatLockProvider) {
        this.bookingFailureMap = new HashMap<>();
        this.allowedRetries = allowedRetries;
        this.seatLockProvider = seatLockProvider;
    }


    public void processPaymentFailure(Booking booking, String user) {
        if (!booking.getUser().equals(user)) {
            throw new IllegalArgumentException("User is not authorized to re-try the booking");
        }
        bookingFailureMap.putIfAbsent(booking, 0);

        if (bookingFailureMap.get(booking) >= allowedRetries) {
            seatLockProvider.unLockSeat(booking.getShow(), booking.getSeats(), user);
            booking.expire();
        } else {
            bookingFailureMap.put(booking, bookingFailureMap.get(booking) + 1);
        }
    }
}
