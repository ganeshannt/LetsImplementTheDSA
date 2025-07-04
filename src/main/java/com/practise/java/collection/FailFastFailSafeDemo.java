package com.practise.java.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFastFailSafeDemo {
    public static void main(String[] args) throws InterruptedException {
        // ===== FAIL-FAST COLLECTIONS =====

        // Example 1: Guaranteed fail-fast behavior with ArrayList
        List<String> failFastList = new ArrayList<>();
        failFastList.add("Apple");
        failFastList.add("Banana");
        failFastList.add("Cherry");
        failFastList.add("Date");

        System.out.println("FAIL-FAST BEHAVIOR:");
        try {
            // Using explicit iterator to guarantee fail-fast behavior
            Iterator<String> it = failFastList.iterator();
            while (it.hasNext()) {
                String fruit = it.next();
                System.out.println("  Processing: " + fruit);
                if (fruit.equals("Banana")) {
                    // This is the key point - modifying the collection directly
                    // not through the iterator will cause the exception
                    failFastList.remove(fruit);  // This WILL cause exception
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("  Exception! " + e.getClass().getSimpleName() +
                    " - Collection was modified during iteration");
        }

        // Alternative demonstration with enhanced for loop
        List<String> anotherList = new ArrayList<>();
        anotherList.add("One");
        anotherList.add("Two");
        anotherList.add("Three");

        System.out.println("\nALTERNATIVE FAIL-FAST DEMONSTRATION:");
        try {
            for (String item : anotherList) {
                System.out.println("  Processing: " + item);
                // Adding a new element during iteration reliably causes exception
                if (item.equals("Two")) {
                    anotherList.add("Four");  // More reliably triggers the exception
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("  Exception! " + e.getClass().getSimpleName() +
                    " - Collection was modified during iteration");
        }

        // Rest of the examples as before...

        // Example 2: Safe ways to modify fail-fast collections
        System.out.println("\nSAFE TECHNIQUES WITH FAIL-FAST COLLECTIONS:");

        // Method 1: Using Iterator's remove() method
        List<String> list1 = new ArrayList<>(Arrays.asList("Dog", "Cat", "Bird"));
        Iterator<String> iterator = list1.iterator();
        while (iterator.hasNext()) {
            String animal = iterator.next();
            if (animal.equals("Cat")) {
                iterator.remove();  // Safe removal using iterator
            }
        }
        System.out.println("  Using iterator.remove(): " + list1);

        // Method 2: Using removeIf (Java 8+)
        List<String> list2 = new ArrayList<>(Arrays.asList("Dog", "Cat", "Bird"));
        list2.removeIf(animal -> animal.equals("Cat"));
        System.out.println("  Using removeIf(): " + list2);

        // Method 3: Creating a copy for iteration
        List<String> list3 = new ArrayList<>(Arrays.asList("Dog", "Cat", "Bird"));
        new ArrayList<>(list3).forEach(animal -> {
            if (animal.equals("Cat")) {
                list3.remove(animal);  // Safe because we're iterating over a copy
            }
        });
        System.out.println("  Using copy for iteration: " + list3);

        // ===== FAIL-SAFE COLLECTIONS =====

        // Example 3: Basic fail-safe behavior with CopyOnWriteArrayList
        List<String> failSafeList = new CopyOnWriteArrayList<>();
        failSafeList.add("Red");
        failSafeList.add("Green");
        failSafeList.add("Blue");

        System.out.println("\nFAIL-SAFE BEHAVIOR:");
        for (String color : failSafeList) {
            System.out.println("  Processing: " + color);
            if (color.equals("Green")) {
                failSafeList.remove(color);      // No exception thrown
                failSafeList.add("Yellow");      // Modification won't be seen in this iteration
                System.out.println("  Modified collection without exception");
            }
        }
        System.out.println("  Collection after iteration: " + failSafeList);

        // Multi-threaded demonstration for clearer contrast
        System.out.println("\nMULTI-THREADED DEMONSTRATION:");

        // Fail-fast map
        final Map<Integer, String> hashMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            hashMap.put(i, "Val-" + i);
        }

        // Fail-safe map
        final Map<Integer, String> concurrentMap = new ConcurrentHashMap<>(hashMap);

        // Create a task that modifies the maps while they're being iterated
        Runnable modifier = () -> {
            try {
                Thread.sleep(50);  // Give iterator time to start
                for (int i = 10; i < 15; i++) {
                    hashMap.put(i, "New-" + i);
                    concurrentMap.put(i, "New-" + i);
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Start the modifier thread
        new Thread(modifier).start();

        // Try to iterate the fail-fast HashMap
        System.out.println("  Iterating HashMap (fail-fast):");
        try {
            for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
                System.out.println("    " + entry.getKey() + " = " + entry.getValue());
                Thread.sleep(20);  // Slow iteration to ensure modification happens during iteration
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("    Exception! " + e.getClass().getSimpleName() +
                    " - HashMap was modified during iteration");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Try to iterate the fail-safe ConcurrentHashMap
        System.out.println("\n  Iterating ConcurrentHashMap (fail-safe):");
        try {
            for (Map.Entry<Integer, String> entry : concurrentMap.entrySet()) {
                System.out.println("    " + entry.getKey() + " = " + entry.getValue());
                Thread.sleep(20);  // Slow iteration to ensure modification happens during iteration
            }
            System.out.println("    Completed without exception!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nSUMMARY OF KEY DIFFERENCES:");
        System.out.println("  Fail-Fast Collections:");
        System.out.println("  - Throw ConcurrentModificationException when modified during iteration");
        System.out.println("  - Use a modification count mechanism to detect changes");
        System.out.println("  - Not suitable for concurrent access without external synchronization");

        System.out.println("\n  Fail-Safe Collections:");
        System.out.println("  - Don't throw exceptions when modified during iteration");
        System.out.println("  - Work on a snapshot of the data at the time the iterator was created");
        System.out.println("  - Thread-safe, but iterators may not reflect the latest state");
    }
}


