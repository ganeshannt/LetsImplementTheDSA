package com.practise.lld.ticketbooking.api;


import com.practise.lld.ticketbooking.service.ShowService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.time.Duration;
import java.time.ZonedDateTime;

@AllArgsConstructor
public class ShowController {
    private final ShowService showService;

    public String createShow(@NonNull String name, @NonNull ZonedDateTime startTime, @NonNull Duration durationInSeconds, String movieId, String screenId) {
        return showService.createShow(name, startTime, durationInSeconds, movieId, screenId).getId();
    }

}
