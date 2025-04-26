package com.practise.lld.ticketbooking.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Theatre {
    private final String id;
    private final String name;
    private final List<Screen> screens;

    public Theatre(String id, String name, List<Screen> screens) {
        this.id = id;
        this.name = name;
        this.screens = screens;
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }
}
