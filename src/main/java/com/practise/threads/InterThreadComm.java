package com.practise.threads;

public class InterThreadComm {

    public static void main(String[] args) {
        System.out.println("Inter Thread Communication Example");
        System.out.println("Execution started by " + Thread.currentThread().getName());
        InterThreadComm interThreadComm = new InterThreadComm();

        SharedCounter sharedCounter = interThreadComm.new SharedCounter();

        Thread incrementThread = new Thread(interThreadComm.new IncrementTask(sharedCounter), "IncrementThread");
        Thread decrementThread = new Thread(interThreadComm.new DecrementTask(sharedCounter), "DecrementThread");

        incrementThread.start();
        decrementThread.start();
    }

    class SharedCounter {
        int count = 0;

        synchronized void increment() {
            while (count >= 5) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            count++;
            System.out.printf("Thread: %s , count %d%n", Thread.currentThread().getName(), count);
            notifyAll();
        }

        synchronized void decrement() {
            while (count <= 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            count--;

            System.out.printf("Thread: %s , count %d%n", Thread.currentThread().getName(), count);
            notifyAll();
        }
    }

    class IncrementTask implements Runnable {

        private final SharedCounter sharedCounter;

        public IncrementTask(SharedCounter sharedCounter) {
            this.sharedCounter = sharedCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                sharedCounter.increment(); // increment and notify
                try {
                    Thread.sleep(1000); // then sleep
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    class DecrementTask implements Runnable {

        private final SharedCounter sharedCounter;

        public DecrementTask(SharedCounter sharedCounter) {
            this.sharedCounter = sharedCounter;
        }

        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                sharedCounter.decrement(); // decrement and notify
                try {
                    Thread.sleep(1000); // then sleep
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
