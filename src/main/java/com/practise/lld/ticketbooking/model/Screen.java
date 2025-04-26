package com.practise.lld.ticketbooking.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Screen {
    private final String id;
    private final String name;
    private final Theatre theatre;
    private final List<Seat> seats;

    public Screen(String id, String name, Theatre theatre, List<Seat> seats) {
        this.id = id;
        this.name = name;
        this.theatre = theatre;
        this.seats = seats;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }
}
