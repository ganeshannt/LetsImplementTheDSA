package com.practise.threads;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {

    private final Queue<Integer> queue;
    private final int capacity;

    BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new BlockingQueue(5);

        // Producer thread to add items to the queue
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                blockingQueue.add(i);
            }
        }, "Producer");

        // Consumer thread to remove items from the queue
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                blockingQueue.remove();
            }
        }, "Consumer");

        t1.start(); // Start the producer thread
        t2.start(); // Start the consumer thread
    }

    boolean add(int item) {
        synchronized (queue) {
            /* While loop is used instead of if to handle spurious wakeups - unexpected wakeups of threads that are waiting for some condition to become true.
             * Imagine, you are waiting for your turn to play with a toy. Your friend tells you to wait until they are done.
             * Sometimes, you might think they are done, but they are not. So, you need to keep checking if they are really done before you start playing.
             * Using while is like you keep asking, "Are you done?" until the answer is "Yes". Using if is like asking only once and assuming the answer won't change, which might not be true.
             */
            while (queue.size() == capacity) {
                System.out.println(Thread.currentThread().getName() + " condition check to add item: " + item);
                try {
                    System.out.println(Thread.currentThread().getName() + " waiting to add item");
                    queue.wait(); // Releases the lock and waits
                    // The following line will not execute until the thread is notified and reacquires lock again
                    System.out.println(Thread.currentThread().getName() + " waiting thread awaken to add item: " + item);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add(item);
            System.out.println(Thread.currentThread().getName() + " added item: " + item);
            queue.notifyAll();
            return true;
        }
    }

    boolean remove() {
        synchronized (queue) { // Synchronize on the queue object to ensure thread safety
            // Use while loop to handle spurious wakeups and recheck the condition
            while (queue.isEmpty()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " waiting to remove item");
                    queue.wait(); // Releases the lock and waits until notified
                    System.out.println(Thread.currentThread().getName() + " waiting thread awaken to remove item: " + queue.peek());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int item = queue.poll(); // Remove the item from the queue
            System.out.println(Thread.currentThread().getName() + " removed item: " + item);
            queue.notifyAll(); // Notify all waiting threads that the condition may have changed
            return true;
        }
    }
}
