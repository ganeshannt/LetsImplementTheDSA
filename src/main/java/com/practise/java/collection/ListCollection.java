package com.practise.java.collection;


import java.util.*;

public class ListCollection {

    private static void arrayListExample() {
        /*
         * ArrayList is a resizable array implementation of the List interface.
         * It allows random access to elements and is not synchronized.
         */

        List<Integer> arrayList = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        arrayList.add(6);
        arrayList.add(3); // Duplicate element will be added
        arrayList.remove(Integer.valueOf(2));

        System.out.println("ArrayList elements:");
        arrayList.forEach(System.out::println);
    }

    private static void linkedListExample() {
        /*
         * LinkedList is a doubly linked list implementation of the List and Deque interfaces.
         * It allows sequential access to elements and is not synchronized.
         */

        List<Integer> linkedList = new LinkedList<>(List.of(1, 2, 3, 4, 5));
        linkedList.add(6);
        linkedList.add(3); // Duplicate element will be added
        linkedList.remove(Integer.valueOf(2));

        System.out.println("LinkedList elements:");
        linkedList.forEach(System.out::println);
    }

    private static void vectorExample() {
        /*
         * Vector is a synchronized, resizable array implementation of the List interface.
         * It is thread-safe but has lower performance compared to ArrayList.
         * Why?
         * - Because of the synchronization overhead.
         */

        List<Integer> vector = new Vector<>(List.of(1, 2, 3, 4, 5));
        vector.add(6);
        vector.add(3); // Duplicate element will be added
        vector.remove(Integer.valueOf(2));

        System.out.println("Vector elements:");
        vector.forEach(System.out::println);
    }

    private static void stackExample() {
        /*
         * Stack is a subclass of Vector that implements a last-in-first-out (LIFO) stack of elements.
         * It is synchronized and thread-safe.
         */

        Stack<Integer> stack = new Stack<>();
        stack.addAll(List.of(1, 2, 3, 4, 5));
        stack.push(6);
        stack.push(3); // Duplicate element will be added
        stack.pop(); // Removes the top element

        System.out.println("Stack elements:");
        stack.forEach(System.out::println);
    }

    public static void main(String[] args) {
        arrayListExample();
        linkedListExample();
        vectorExample();
        stackExample();
    }
}