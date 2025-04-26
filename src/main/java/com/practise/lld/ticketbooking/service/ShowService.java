package com.practise.lld.ticketbooking.service;

import com.practise.lld.ticketbooking.model.Movie;
import com.practise.lld.ticketbooking.model.Screen;
import com.practise.lld.ticketbooking.model.Show;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShowService {
    private final Map<String, Show> showMap;
    private final MovieService movieService;
    private final TheatreService theatreService;

    public ShowService(Map<String, Show> showMap, MovieService movieService, TheatreService theatreService) {
        this.movieService = movieService;
        this.theatreService = theatreService;
        this.showMap = new HashMap<>();
    }

    public Show getShow(String id) {
        return showMap.get(id);
    }


    public Show createShow(String name, ZonedDateTime startTime, Duration durationInSeconds, String movieId, String screenId) {
        Movie movie = movieService.getMovie(movieId);
        if (movie == null) {
            throw new IllegalArgumentException("Movie not found");
        }
        Screen screen = theatreService.getScreen(screenId);
        if (screen == null) {
            throw new IllegalArgumentException("Screen not found");
        }
        Show show = new Show(UUID.randomUUID().toString(), name, startTime, durationInSeconds, movie, screen);
        showMap.put(show.getId(), show);
        return show;
    }
}
