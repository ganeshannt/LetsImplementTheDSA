package com.practise.lld.ticketbooking.api;

import com.practise.lld.ticketbooking.model.Screen;
import com.practise.lld.ticketbooking.service.TheatreService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

@AllArgsConstructor
public class TheatreController {
    private final TheatreService theatreService;

    public String createTheatre(@NonNull String name, @NonNull List<Screen> screenList) {
        return theatreService.createTheatre(name, screenList).getId();
    }

    public String createScreenInTheatre(@NonNull String theatreId, @NonNull String name) {
        return theatreService.createScreenInTheatre(theatreId, name).getId();
    }

    public String createSeatInScreen(@NonNull final Integer rowNo, @NonNull final Integer seatNo, @NonNull final String screenId) {
        return theatreService.createSeatInScreen(rowNo, seatNo, screenId).getId();
    }
}
