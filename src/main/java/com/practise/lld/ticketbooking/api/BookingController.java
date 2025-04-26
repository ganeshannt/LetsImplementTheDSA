package com.practise.lld.ticketbooking.api;


import com.practise.lld.ticketbooking.model.Seat;
import com.practise.lld.ticketbooking.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    public String bookTicket(@NonNull final String showId, @NonNull final String userId, @NonNull final List<Seat> seats) {
        return bookingService.bookTicket(showId, userId, seats).getId();
    }

}
