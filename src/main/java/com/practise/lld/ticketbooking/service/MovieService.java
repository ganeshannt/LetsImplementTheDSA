package com.practise.lld.ticketbooking.service;

import com.practise.lld.ticketbooking.model.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class MovieService {

    private final Map<String, Movie> movies;

    public MovieService() {
        this.movies = new HashMap<>();
    }

    public Movie getMovie(String id) {
        return movies.get(id);
    }

    public Movie createMovie(String name) {
        Movie movie = new Movie(UUID.randomUUID().toString(), name);
        movies.put(movie.getId(), movie);
        return movie;
    }
}
