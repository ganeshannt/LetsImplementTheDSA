package com.practise.lld.meetingscheduler;

/**
 * @author Ganeshan Nagarajan
 * @since 24/11/24
 */

class MeetingSchedulerApp {

    public static void main(String[] args) {

        Scheduler scheduler = new Scheduler("Room 1", "Room 2", "Room 3");

        scheduler.book(1, 10, 12);
        scheduler.book(1, 10, 12);
        scheduler.book(1, 10, 12);
        scheduler.book(1, 10, 12);

    }
}
