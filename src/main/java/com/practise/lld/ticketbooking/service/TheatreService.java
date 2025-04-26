package com.practise.lld.ticketbooking.service;


import com.practise.lld.ticketbooking.model.Screen;
import com.practise.lld.ticketbooking.model.Seat;
import com.practise.lld.ticketbooking.model.Theatre;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class TheatreService {
    private final Map<String, Theatre> theatres;
    private final Map<String, Screen> screens;
    private final Map<String, Seat> seats;

    public Theatre getTheatre(String theatreId) {
        return theatres.get(theatreId);
    }

    public Theatre createTheatre(String name, List<Screen> screenList) {
        Theatre theatre = new Theatre(UUID.randomUUID().toString(), name, screenList);
        theatres.put(theatre.getId(), theatre);
        return theatre;
    }

    public Screen getScreen(String screenId) {
        return screens.get(screenId);
    }

    public Screen createScreenInTheatre(String theatreId, String name) {
        Theatre theatre = theatres.get(theatreId);
        Screen screen = new Screen(UUID.randomUUID().toString(), name, theatre, new ArrayList<>());
        theatre.addScreen(screen);
        screens.put(screen.getId(), screen);
        return screen;
    }

    public Seat getSeat(String seatId) {
        return seats.get(seatId);
    }

    public Seat createSeatInScreen(Integer rowNo, Integer seatNo, String screenId) {
        Screen screen = screens.get(screenId);
        Seat seat = new Seat(UUID.randomUUID().toString(), rowNo, seatNo);
        screen.addSeat(seat);
        seats.put(seat.getId(), seat);
        return seat;
    }
}
