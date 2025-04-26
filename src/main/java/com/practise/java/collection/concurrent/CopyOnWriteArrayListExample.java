package com.practise.java.collection.concurrent;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 *  where we have to use CopyOnWriteArrayList instead of explicit Synchronization
 *  CopyOnWriteArrayList is a thread-safe variant of ArrayList in which all mutative operations (add, set, and so on) are implemented by making a fresh copy of the underlying array.
 *  if you use Synchronization on ArrayList then it will be slow because of the lock and unlock happen on read and write operation.
 *
 *
 * when we have to choose CopyOnWriteArrayList over ArrayList
 * 1. When we have more read operation than write operation in the thread-safe environment.
 * 2. When we have to iterate the list and at the same time, we have to add or remove the element from the list.
 *
 * What is mean by array snapshot in CopyOnWriteArrayList reading?
 * 1. When we create an iterator on CopyOnWriteArrayList, it will create a snapshot of the array.
 * 2. is it a copy of an array? - No, it is not a copy of an array. It is a reference to the array.
 *
 * */

public class CopyOnWriteArrayListExample {

    public static void main(String[] args) throws InterruptedException {
        // 1. Initializing the CopyOnWriteArrayList with 5 elements
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("Element 1");
        list.add("Element 2");
        list.add("Element 3");

        System.out.println("Initial list: " + list);

        // Thread 1: Iterating through the list
        Thread thread1 = new Thread(() -> {
            Iterator<String> iterator = list.iterator();
            System.out.println("Thread 1 - Iterator created.  Iterating...");
            while (iterator.hasNext()) {
                String element = iterator.next();
                System.out.println("Thread 1 - Element: " + element);
                try {
                    Thread.sleep(100); // Simulate some processing time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Thread 1 - Finished iterating.");
        });

        // Thread 2: Adding an element to the list while Thread 1 is iterating
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(50); // Ensure thread1 starts iterating first
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add("Element 4");
            System.out.println("Thread 2 - Added 'Element 6'. List is now: " + list);
        });

        // Thread 3: Iterating after thread2 has added an element
        Thread thread3 = new Thread(() -> {
            try {
                Thread.sleep(200); // Ensure thread1 and thread2 have completed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Iterator<String> iterator = list.iterator();
            System.out.println("Thread 3 - Iterator created. Iterating...");
            while (iterator.hasNext()) {
                String element = iterator.next();
                System.out.println("Thread 3 - Element: " + element);
            }
            System.out.println("Thread 3 - Finished iterating.");
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Final list: " + list);
    }
}
