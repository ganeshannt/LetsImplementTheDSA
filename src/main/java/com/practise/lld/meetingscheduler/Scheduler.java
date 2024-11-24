package com.practise.lld.meetingscheduler;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ganeshan Nagarajan
 * @since 24/11/24
 */

@Getter
@Setter
class Scheduler {

    private Set<Room> roomSet;

    Scheduler(String... names) {
        this.roomSet = new HashSet<>();
        for (String name : names) {
            roomSet.add(new Room(name));
        }
    }

    boolean book(int day, int start, int end) {
        for (Room room : roomSet) {
            if (room.book(day, start, end)) {
                return true;
            }
        }
        return false;
    }
}
