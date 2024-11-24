package com.practise.lld.meetingscheduler;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Ganeshan Nagarajan
 * @since 24/11/24
 */

@Getter
@Setter
class Room {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private String name;
    private Map<Integer, Set<Meeting>> calender;

    Room(String name) {
        this.name = name;
        this.calender = new HashMap<>();
    }


    boolean book(int day, int start, int end) {
        calender.putIfAbsent(day, new HashSet<>());
        for (Meeting m : calender.get(day)) {
            if ((start <= m.getStartTime() && end >= m.getStartTime()) || (start <= m.getEndTime() && end >= m.getEndTime())) {
                logger.warning("Some other meeting booked at the same time. Room: " + m.getRoom() + " start_time :" + m.getStartTime() + " end_time:" + m.getEndTime());
                return false;
            }
        }
        Set<Meeting> meeting = calender.get(day);
        meeting.add(new Meeting(start, end, this));
        calender.put(day, meeting);
        logger.info("meeting booked successfully. Room: " + this.name + " start_time :" + start + " end_time:" + end);
        return true;
    }
}



