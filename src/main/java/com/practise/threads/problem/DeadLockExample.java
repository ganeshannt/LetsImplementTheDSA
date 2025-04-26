package com.practise.threads.problem;

public class DeadLockExample {

    public static void main(String[] args) {

        System.out.println("LambdaTester thread starting");

        /*
         *
         * T1 locks resource1 using lock1 and waits for resource2
         * t2 locks resource2 using lock2 and waits for resource1
         *
         * T1 and t2 are waiting for each other to release the lock on resource2 and resource1 respectively.it is a deadlock.
         *
         * Here to avoid lock, use lock ordering. Lock the resources in the same order in all the threads.
         *
         * To avoid deadlock, we can use below approaches:
         * 1. Avoid nested locks
         * 2. Avoid multiple locks
         * 3. Avoid locks in loops
         * 4. Use lock ordering
         * 5. Use lock timeouts
         *
         * */

        final String lock1 = "resource1";
        final String lock2 = "resource2";
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " locked " + lock1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " waiting for " + lock2);
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " locked " + lock2);
                }
            }
        }, "Thread1");

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " locked " + lock2);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " waiting for " + lock1);
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " locked " + lock1);
                }
            }
        }, "Thread2");

        t1.start();
        t2.start();

        System.out.println("LambdaTester thread exiting");
    }
}
