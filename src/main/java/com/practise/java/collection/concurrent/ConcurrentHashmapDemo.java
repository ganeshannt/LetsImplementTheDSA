package com.practise.java.collection.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/*
 *  1. Concurrent Hashmap is a thread-safe version of Hashmap.
 *  2. It locks only a segment of the map, not the whole map while adding or removing elements.
 *
 *
 * How Concurrent HashMap Internally Manages Locks and Segmentation
ConcurrentHashMap in Java uses a sophisticated locking mechanism that has evolved across Java versions:

* In Java 7 and earlier:

    The map was divided into segments (default: 16 segments)
    Each segment had its own lock (ReentrantLock)
    Operations would lock only the relevant segment rather than the entire map
    This approach was called "segmented locking" or "bucket locking"

* In Java 8 and later:
    The implementation was completely redesigned
    Instead of segments, it uses a node-based approach
    It employs a combination of CAS (Compare-And-Swap) operations and synchronized blocks on nodes
    For put operations:
    If the bucket is empty, it uses CAS to add the first node (no locking)
    If the bucket has nodes, it synchronizes on the first node of the bucket
    This finer-grained locking provides better concurrency
 *
 * */


public class ConcurrentHashmapDemo {
    private static final int NUM_THREADS = 5;
    private static final int NUM_INSERTIONS = 100;

    private static ConcurrentHashMap<String, Integer> hashMap = new ConcurrentHashMap<>();

    //private static HashMap<String, Integer> hashMap = new HashMap<>(); - this will give an incorrect result here


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        for (int i = 0; i < NUM_THREADS; i++) {
            executorService.execute(insertRecord());
        }

        executorService.shutdown();

        if (!executorService.isTerminated()) {
            Thread.sleep(1000);
        }

        System.out.println("Size of the hashmap = " + hashMap.size());
    }

    private static Runnable insertRecord() {
        return () -> {
            for (int i = 0; i < NUM_INSERTIONS; i++) {
                hashMap.put(i + Thread.currentThread().getName(), i);
            }
        };
    }
}
