package com.practise.playground.interview;


/*
 * Rate limit : 5 request / 10 sec from 1 machine <ipaddress>
 * */


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class RateLimiting {

    Map<String, ArrayDeque<LocalDateTime>> rateLimitingMap = new HashMap<>();


    boolean isAllowed(String ip) {
        if (rateLimitingMap.containsKey(ip)) {
            ArrayDeque<LocalDateTime> queue = rateLimitingMap.get(ip);
            if (queue.size() < 5) {
                if (queue.getLast().isBefore(LocalDateTime.now().minus(10, ChronoUnit.SECONDS))) {
                    return true;
                } else {
                    return false;
                }
            }
            queue.clear();
            queue.addFirst(LocalDateTime.now());
            rateLimitingMap.put(ip, queue);
        } else {
            ArrayDeque<LocalDateTime> arrayDeque = new ArrayDeque<>();
            arrayDeque.add(LocalDateTime.now());
            rateLimitingMap.put(ip, arrayDeque);
        }
        return true;
    }


    public static void main(String[] args) {

    }
}
