package com.practise.lld.ticketbooking.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Duration;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Show {
    private String id;
    private String name;
    private ZonedDateTime startTime;
    private Duration durationInSeconds;
    private Movie movie;
    private Screen screen;
}
