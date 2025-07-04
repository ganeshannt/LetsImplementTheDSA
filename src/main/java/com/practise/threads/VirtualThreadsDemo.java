package com.practise.threads;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

class VirtualThreadsDemo {

    // How many tasks we want to run concurrently
    private static final int NUM_TASKS = 100_000; // Let's try a lot!

    public static void main(String[] args) {

        System.out.println("Starting Virtual Threads Demo (Requires Java 21+)");
        System.out.printf("Attempting to run %,d concurrent tasks...\n", NUM_TASKS);

        Instant start = Instant.now();

        // --- Scenario 1: Using Executors.newVirtualThreadPerTaskExecutor() ---
        // This is the RECOMMENDED way to manage many virtual threads.
        // It creates a new virtual thread for each submitted task.
        // Using try-with-resources ensures the executor is properly shut down.
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            IntStream.range(0, NUM_TASKS).forEach(i -> {
                executor.submit(() -> {
                    // Simulate some work that involves waiting (like I/O)
                    try {
                        // Print which task is running and on what kind of thread
                        // System.out.printf("Task %d running on thread: %s (isVirtual=%b)\n",
                        //         i, Thread.currentThread().getName(), Thread.currentThread().isVirtual());

                        Thread.sleep(Duration.ofMillis(10)); // Simulate waiting

                        // System.out.printf("Task %d finished on thread: %s\n", i, Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore interrupt status
                        System.err.println("Task " + i + " interrupted.");
                    }
                });
            });

            // The try-with-resources block automatically calls executor.close()
            // which waits for all submitted tasks to complete.
            System.out.println("All tasks submitted to executor. Waiting for completion...");

        } // Executor is automatically closed here

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);

        System.out.printf("%,d tasks completed in %d milliseconds.\n", NUM_TASKS, duration.toMillis());
        System.out.println("Note how quickly many waiting tasks can complete concurrently!");

        // --- Scenario 2: Creating a single virtual thread directly (less common for many tasks) ---
        System.out.println("\n--- Demonstrating direct virtual thread creation ---");

        Thread vt = Thread.startVirtualThread(() -> {
            System.out.println("Direct virtual thread running: " + Thread.currentThread());
            System.out.println("Is this a virtual thread? " + Thread.currentThread().isVirtual());
            try {
                Thread.sleep(Duration.ofSeconds(1));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Direct virtual thread finished.");
        });

        // You might need to wait for it if your main thread finishes too quickly
        try {
            vt.join(); // Wait for the direct virtual thread to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // --- Scenario 3: Using a ThreadFactory (alternative way with Executor) ---
        System.out.println("\n--- Demonstrating virtual thread factory ---");
        ThreadFactory virtualThreadFactory = Thread.ofVirtual().factory();
        try (var executor = Executors.newThreadPerTaskExecutor(virtualThreadFactory)) {
            executor.submit(() -> System.out.println("Task running via virtual thread factory: " + Thread.currentThread()));
        } // Auto-close and wait

        System.out.println("\nDemo finished.");
    }
}

