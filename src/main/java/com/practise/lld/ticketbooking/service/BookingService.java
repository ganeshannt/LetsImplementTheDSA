package com.practise.lld.ticketbooking.service;


import com.practise.lld.ticketbooking.model.Booking;
import com.practise.lld.ticketbooking.model.Seat;
import com.practise.lld.ticketbooking.model.Show;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookingService {
    private final Map<String, Booking> bookingMap;
    private final ShowService showService;

    public BookingService(ShowService showService) {
        this.showService = showService;
        this.bookingMap = new HashMap<>();
    }

    public Booking getBooking(String bookingId) {
        return bookingMap.get(bookingId);
    }

    public Booking bookTicket(String showId, String userId, List<Seat> seats) {
        Show show = showService.getShow(showId);
        Booking booking = new Booking(UUID.randomUUID().toString(), show, userId, seats);
        bookingMap.put(booking.getId(), booking);
        return booking;
    }

    public void confirmBooking(String bookingId, String userId) {
        Booking booking = bookingMap.get(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Invalid booking id");
        }
        if (!booking.getUser().equals(userId)) {
            throw new IllegalArgumentException("User is not authorized to confirm the booking");
        }
        booking.confirm();
    }
}
