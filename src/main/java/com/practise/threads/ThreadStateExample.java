package com.practise.threads;

public class ThreadStateExample {
    private static final Object lock = new Object();
    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new SampleTask1(), "SampleThread");
        Thread thread2 = new Thread(new SampleTask2(), "SampleThread2");

        // NEW
        System.out.println("1. State after thread creation: " + thread1.getState());

        thread1.start();

        // RUNNABLE
        System.out.println("2. State after thread start: " + thread1.getState());

        Thread.sleep(100); // Give some time for SampleThread to start sleeping

        // TIMED_WAITING
        System.out.println("3. State during sleep: " + thread1.getState());

        Thread.sleep(1000); // Wait for SampleThread's sleep to finish

        while (!flag) {
            System.out.println("Waiting for SampleThread to acquire lock");
            Thread.sleep(10); // Wait for SampleThread to enter WAITING state
        }

        // WAITING
        System.out.println("4. State when waiting on lock: " + thread1.getState());

        synchronized (lock) {
            System.out.println("LambdaTester thread notifying SampleThread");
            lock.notify(); // Notify SampleThread to wake up
        }

        thread2.start();

        Thread.sleep(100); // Give some time for SampleThread1 to try acquiring the lock again

        // now thread 1 has lock and thread 2 is trying to acquire lock so now thread 2 will be in BLOCKED state
        System.out.println("5. State when trying to acquire lock: " + thread2.getState());

        thread1.join(1000); // Wait for SampleThread to finish or timeout after 1 second

        // TERMINATED (or possibly still RUNNABLE if join timed out)
        System.out.println("6. Final state of thread: " + thread1.getState());
    }

    static class SampleTask1 implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("SampleThread1 sleeping");
                Thread.sleep(500); // TIMED_WAITING state
                synchronized (lock) {
                    System.out.println("SampleThread1 acquired lock");
                    flag = true;
                    System.out.println("SampleThread1 waiting on lock");
                    lock.wait(); // WAITING state
                    System.out.println("SampleThread1 woke up");
                }
                synchronized (lock) {
                    // This might cause BLOCKED state if thread2 tries to acquire lock
                    System.out.println("SampleThread1 acquired lock again");
                    Thread.sleep(500);
                    System.out.println("SampleThread1 releasing lock");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class SampleTask2 implements Runnable {
        @Override
        public void run() {
            try {
                synchronized (lock) {
                    System.out.println("SampleThread2 acquired lock");
                    Thread.sleep(100);
                    System.out.println("SampleThread2 releasing lock");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
