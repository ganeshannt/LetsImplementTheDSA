package com.practise.threads.problem;


/*
* Details on volatile keyword:
* CPU caches use fast SRAM (not DRAM) and are associated with CPU cores.
Threads run on cores and utilize those cores' caches.
The potential for inconsistency arises when multiple threads (on potentially different cores with different caches) access the same non-volatile variable.
The volatile keyword ensures visibility by forcing reads and writes of that specific variable to synchronize with main memory (DRAM), bypassing the potentially stale values in the core's SRAM cache.
* */

class Vehicle {
    // "volatile" keyword instruct JVM to read the value of a variable from the main memory(RAM) instead of thread cache
    //Without volatile, each thread might have its own cached copy of a variable. This can lead to inconsistencies if one thread modifies the variable, and other threads continue to use their outdated cached values.
    private static volatile Vehicle vehicleInstance = null;

    //    private constructor to prevent client applications from using constructor
    private Vehicle() {
    }


    public static Vehicle getInstance() {
        if (vehicleInstance == null) {
//            we cannot use this keyword inside static block, so class name is used as a lock object
            synchronized (Vehicle.class) {
                if (vehicleInstance == null) { // double check
                    vehicleInstance = new Vehicle();
                }
            }
        }
        return vehicleInstance;
    }
}


public class MultiThreadSingletonExample {
    public static void main(String[] args) {
        Vehicle v = Vehicle.getInstance();
    }
}
