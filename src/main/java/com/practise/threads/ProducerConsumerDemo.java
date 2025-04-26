package com.practise.threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Represents the shared SharedBuffer between Producer and Consumer.
 * Handles synchronization and waiting/notifying.
 */
class SharedBuffer {
    private Queue<Integer> queue;
    private int capacity;

    public SharedBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("SharedBuffer capacity must be positive.");
        }
        this.queue = new LinkedList<>();
        this.capacity = capacity;
        System.out.println("SharedBuffer created with capacity: " + capacity);
    }

    /**
     * Produces (adds) an item to the SharedBuffer.
     * Waits if the SharedBuffer is full.
     * Notifies consumers after adding.
     *
     * @param item The item to add.
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    public synchronized void produce(int item) throws InterruptedException {
        // Use 'while' instead of 'if' for the condition check.
        // This guards against spurious wakeups - the thread might wake up
        // even if not notified, so it must re-check the condition.
        while (queue.size() == capacity) {
            System.out.println("Producer [" + Thread.currentThread().getName() + "] SharedBuffer FULL. Waiting...");
            wait(); // Releases the lock and waits
            System.out.println("Producer [" + Thread.currentThread().getName() + "] Woke up! Checking SharedBuffer again...");
        }

        // Add the item to the queue
        queue.add(item);
        System.out.println("Producer [" + Thread.currentThread().getName() + "] Produced: " + item + " (SharedBuffer size: " + queue.size() + ")");

        // Notify potentially waiting consumers that an item is now available.
        // notifyAll() is safer than notify() in complex scenarios.
        notifyAll();
    }

    /**
     * Consumes (removes) an item from the SharedBuffer.
     * Waits if the SharedBuffer is empty.
     * Notifies producers after removing.
     *
     * @return The consumed item.
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    public synchronized int consume() throws InterruptedException {
        // Use 'while' for the condition check (guards against spurious wakeups)
        while (queue.isEmpty()) {
            System.out.println("Consumer [" + Thread.currentThread().getName() + "] SharedBuffer EMPTY. Waiting...");
            wait(); // Releases the lock and waits
            System.out.println("Consumer [" + Thread.currentThread().getName() + "] Woke up! Checking SharedBuffer again...");
        }

        // Remove the item from the queue
        int item = queue.remove();
        System.out.println("Consumer [" + Thread.currentThread().getName() + "] Consumed: " + item + " (SharedBuffer size: " + queue.size() + ")");

        // Notify potentially waiting producers that space is now available.
        notifyAll();

        return item;
    }

    // Optional: Method to check current size (mostly for debugging/info)
    public synchronized int getSize() {
        return queue.size();
    }
}

/**
 * The Producer thread - generates items and puts them into the SharedBuffer.
 */
class Producer implements Runnable {
    private SharedBuffer SharedBuffer;
    private Random random = new Random();

    public Producer(SharedBuffer SharedBuffer) {
        this.SharedBuffer = SharedBuffer;
    }

    @Override
    public void run() {
        try {
            int itemCounter = 0;
            while (true) { // Run indefinitely for demonstration
                itemCounter++;
                int item = itemCounter; // Simple item generation
                // int item = random.nextInt(100); // Alternative: random items

                System.out.println("Producer [" + Thread.currentThread().getName() + "] Trying to produce: " + item);
                SharedBuffer.produce(item);

                // Simulate time taken to produce the next item
                Thread.sleep(random.nextInt(500) + 100); // Sleep 100-600ms
            }
        } catch (InterruptedException e) {
            System.out.println("Producer [" + Thread.currentThread().getName() + "] was interrupted.");
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }
}

/**
 * The Consumer thread - takes items from the SharedBuffer and consumes them.
 */
class Consumer implements Runnable {
    private SharedBuffer SharedBuffer;
    private Random random = new Random();

    public Consumer(SharedBuffer SharedBuffer) {
        this.SharedBuffer = SharedBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) { // Run indefinitely for demonstration
                System.out.println("Consumer [" + Thread.currentThread().getName() + "] Trying to consume...");
                int item = SharedBuffer.consume();

                // Simulate time taken to process (consume) the item
                Thread.sleep(random.nextInt(1000) + 200); // Sleep 200-1200ms
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer [" + Thread.currentThread().getName() + "] was interrupted.");
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }
}

/**
 * Main class to set up and start the Producer and Consumer threads.
 */
public class ProducerConsumerDemo {

    public static void main(String[] args) {
        System.out.println("--- Producer-Consumer Problem Demonstration ---");

        // Define the capacity of the shared SharedBuffer
        int SharedBufferCapacity = 5;
        SharedBuffer sharedSharedBuffer = new SharedBuffer(SharedBufferCapacity);

        // Create producer and consumer tasks
        Runnable producerTask = new Producer(sharedSharedBuffer);
        Runnable consumerTask = new Consumer(sharedSharedBuffer);

        // Create threads for the tasks
        Thread producerThread = new Thread(producerTask, "P1");
        Thread consumerThread1 = new Thread(consumerTask, "C1");
        // You could easily add more consumers or producers:
        // Thread consumerThread2 = new Thread(consumerTask, "C2");

        System.out.println("Starting threads...");

        // Start the threads
        producerThread.start();
        consumerThread1.start();
        // consumerThread2.start(); // Uncomment to run a second consumer

        // Note: In a real application, you'd likely have a mechanism
        // to gracefully shut down these threads instead of letting them
        // run indefinitely.
    }
}
