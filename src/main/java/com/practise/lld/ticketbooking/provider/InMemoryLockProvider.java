package com.practise.lld.ticketbooking.provider;

import com.practise.lld.ticketbooking.model.Seat;
import com.practise.lld.ticketbooking.model.SeatLock;
import com.practise.lld.ticketbooking.model.Show;
import lombok.NonNull;
import lombok.Synchronized;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryLockProvider implements SeatLockProvider {

    private final Map<Show, Map<Seat, SeatLock>> locks;
    private final Integer lockTimeoutInSeconds;

    public InMemoryLockProvider(Integer lockTimeoutInSeconds) {
        this.locks = new HashMap<>();
        this.lockTimeoutInSeconds = lockTimeoutInSeconds;
    }

    @Override
    @Synchronized
    public void lockSeat(@NonNull final Show show, @NonNull final List<Seat> seats, @NonNull final String lockedBy) {
        for (Seat seat : seats) {
            if (!isSeatLocked(show, seat)) {
                SeatLock seatLock = new SeatLock(seat, show, ZonedDateTime.now(), lockedBy, lockTimeoutInSeconds);
                locks.putIfAbsent(show, new HashMap<>());
                locks.get(show).put(seat, seatLock);
            }
        }
    }

    private boolean isSeatLocked(@NonNull Show show, Seat seat) {
        if (locks.containsKey(show) && locks.get(show).containsKey(seat)) {
            return !locks.get(show).get(seat).isLockExpired();
        }
        return false;
    }


    @Override
    @Synchronized
    public void unLockSeat(Show show, List<Seat> seats, String lockedBy) {
        for (Seat seat : seats) {
            if (validateLock(show, seat, lockedBy)) {
                locks.get(show).remove(seat);
            }
        }
    }

    @Override
    public boolean validateLock(Show show, Seat seat, String lockedBy) {
        if (isSeatLocked(show, seat)) {
            return locks.get(show).get(seat).getLockedBy().equals(lockedBy);
        }
        return false;
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        if (locks.containsKey(show)) {
            return locks.get(show).keySet().stream().toList();
        }
        return List.of();
    }
}
