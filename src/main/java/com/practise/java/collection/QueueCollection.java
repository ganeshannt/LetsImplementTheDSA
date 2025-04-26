package com.practise.java.collection;

import java.util.*;

public class QueueCollection {


    private static void queueExample() {

        // Common queue methods
        Queue<Integer> integerQueue = new LinkedList<>(List.of(1, 2, 3, 4, 5));
        integerQueue.offer(3);
        integerQueue.offer(10); // add the element to the end of the queue, but it differs from add method, if the queue is full, it will return false
        integerQueue.add(20); // add the element to the end of the queue, but it differs from offer method, if the queue is full, it will throw IllegalStateException
        integerQueue.poll(); // remove the first element and return it value, if the queue is empty, it will return null
        integerQueue.peek(); // return the first element without removing it, if the queue is empty, it will return null
        integerQueue.element(); // return the first element without removing it, if the queue is empty, it will throw NoSuchElementException
        integerQueue.remove(); // remove the first element and return it value, if the queue is empty, it will throw NoSuchElementException

        System.out.println("Insertion order preserved");
        integerQueue.forEach(System.out::println);
    }


    private static void arrayDequeExample(List<Student> students) {
        /*
         * ArrayDeque is a resizable array implementation of the Deque interface. Internally, it uses a dynamic array to store the elements.
         * It does not allow null elements. Preserve the insertion order.
         * It is not thread-safe.
         * */

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(List.of(1, 2, 3, 4, 5));
        arrayDeque.addFirst(3); // add the element to the front of the queue, but it differs from offerFirst method, if the queue is full, it will throw IllegalStateException
        arrayDeque.addLast(10); // add the element to the end of the queue, but it differs from offerLast method, if the queue is full, it will throw IllegalStateException
        arrayDeque.offerFirst(20); // add the element to the front of the queue, but it differs from addFirst method, if the queue is full, it will return false
        arrayDeque.offerLast(30); // add the element to the end of the queue, but it differs from addLast method, if the queue is full, it will return false
        arrayDeque.pollFirst(); // remove the first element and return it value, if the queue is empty, it will return null
        arrayDeque.pollLast(); // remove the last element and return it value, if the queue is empty, it will return null
        arrayDeque.peekFirst(); // return the first element without removing it, if the queue is empty, it will return null
        arrayDeque.peekLast(); // return the last element without removing it, if the queue is empty, it will return null
        arrayDeque.removeFirst(); // remove the first element and return it value, if the queue is empty, it will throw NoSuchElementException
        arrayDeque.removeLast(); // remove the last element and return it value, if the queue is empty, it will throw NoSuchElementException
        arrayDeque.element(); // return the first element without removing it, if the queue is empty, it will throw NoSuchElementException

        arrayDeque.forEach(System.out::println);


        Queue<Student> studentDeque = new ArrayDeque<>(students);
        studentDeque.offer(new Student(50, 50));
        studentDeque.offer(new Student(100, 100));

        studentDeque.forEach(student -> System.out.println("Maths: " + student.getMaths() + " Physics: " + student.getPhysics()));


    }


    private static void priorityQueueExample(List<Student> students) {

        /*
         * Priority queue is a queue data structure that retrieves elements based on their priority.
         *  A PriorityQueue uses an array as its underlying data structure to represent the binary heap.
         * This array is dynamically resized as needed to accommodate more elements.
         * The array is conceptually arranged as a binary tree.
         * The root of the tree is at index 0.
         * Internal implementation is a binary heap representation of array data structure
         * Using comparable interface, it will decide the priority of the elements.
         * */

        Queue<Student> studentQueue = new PriorityQueue<>(students);

        studentQueue.offer(new Student(50, 50));
        studentQueue.offer(new Student(100, 90));

        System.out.println("Ascending order based on maths marks implementation using Comparable interface");
        while (!studentQueue.isEmpty()) {
            Student student = studentQueue.poll();
            System.out.println("Maths: " + student.getMaths() + " Physics: " + student.getPhysics());
        }

        Queue<Student> studentQueueWithComparator = new PriorityQueue<>(new MyCustomComparator());
        studentQueueWithComparator.addAll(students);
        studentQueueWithComparator.offer(new Student(100, 100));
        studentQueueWithComparator.offer(new Student(50, 50));

        System.out.println("Descending order based on physics marks implementation using Comparator interface");
        while (!studentQueueWithComparator.isEmpty()) {
            Student student = studentQueueWithComparator.poll();
            System.out.println("Maths: " + student.getMaths() + " Physics: " + student.getPhysics());
        }

        System.out.println("Descending order based on maths marks implementation using lambda expression");
        Queue<Student> studentQueueWithLambda = new PriorityQueue<>((o1, o2) -> o2.getMaths() - o1.getMaths());
        studentQueueWithLambda.addAll(students);
        studentQueueWithLambda.offer(new Student(100, 100));
        studentQueueWithLambda.offer(new Student(50, 50));
        while (!studentQueueWithLambda.isEmpty()) {
            Student student = studentQueueWithLambda.poll();
            System.out.println("Maths: " + student.getMaths() + " Physics: " + student.getPhysics());
        }
    }


    public static void main(String[] args) {

        /*
         * Queue is an interface that extends Collection interface.
         * FIFO: use LinkedList implementation of Queue/Deque
         * LIFO: use ArrayDeque implementation of Deque. It is a double-ended queue. You can use Stack class also, but it is legacy class and not recommended to use.
         */

        List<Student> students = List.of(new Student(90, 80), new Student(80, 90), new Student(70, 70), new Student(60, 60));

        queueExample();
        arrayDequeExample(students);
        priorityQueueExample(students);

    }


}
