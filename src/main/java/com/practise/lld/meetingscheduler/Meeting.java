package com.practise.lld.meetingscheduler;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ganeshan Nagarajan
 * @since 24/11/24
 */

@Getter
@Setter
class Meeting {

    private int startTime;
    private int endTime;
    private Room room;

    Meeting(int startTime, int endTime, Room room) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
    }

}
