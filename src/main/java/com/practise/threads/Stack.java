package com.practise.threads;

class Stack {

    final Object lock;
    private int[] array;
    private int stackTop;

    Stack(int capacity) {
        this.array = new int[capacity];
        this.stackTop = -1;
        lock = new Object(); // manually added the lock object to show how a lock object can be used for synchronization
    }

    public static void main(String[] args) {

        System.out.println("LambdaTester thread starting :" + Thread.currentThread().getName());

        Stack stack = new Stack(5);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                stack.push(i);
            }
        });


        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                stack.pop();
            }
        });

        t1.start();
        t2.start();

        System.out.println("LambdaTester thread ending :" + Thread.currentThread().getName());
    }

    boolean push(int element) {
        if (isFull()) return false;
        if (isEmpty()) {
            stackTop = 0;
        }
        synchronized (lock) { // here instead of a lock object, we can use this keyword as it efficiently locks the object
            array[stackTop++] = element;
        }
        System.out.println(Thread.currentThread().getName() + " push-element: " + element + " index: " + (stackTop - 1));
        return true;
    }

    // synchronized keyword can be used in method signature to make the method synchronized.
    // synchronized method internally warp all the code in the method with synchronized block using this keyword as a lock object.
    boolean pop() {
        if (isEmpty()) return false;
        int element = array[stackTop];
        synchronized (lock) { // here instead of a lock object we can use this keyword as it efficiently locks the object
            array[stackTop--] = 0;
        }
        System.out.println(Thread.currentThread().getName() + " pop-element: " + element + " index: " + (stackTop + 1));
        return true;
    }

    boolean isEmpty() {
        return stackTop < 0;
    }

    boolean isFull() {
        return stackTop >= array.length - 1;
    }
}
