package com.practise.lld.ticketbooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Seat {
    private String id;
    private int rowNumber;
    private int seatNumber;
}
