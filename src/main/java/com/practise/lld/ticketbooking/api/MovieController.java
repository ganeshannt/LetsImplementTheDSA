package com.practise.lld.ticketbooking.api;


import com.practise.lld.ticketbooking.model.Movie;
import com.practise.lld.ticketbooking.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    public Movie createMovie(@NonNull String name) {
        return movieService.createMovie(name);
    }

}
