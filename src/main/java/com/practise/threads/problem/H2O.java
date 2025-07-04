package com.practise.threads.problem;

import java.util.concurrent.Semaphore;

class H2O {

    Semaphore hydrogenSemaphore;
    Semaphore oxygenSemaphore;


    public H2O() {
        this.hydrogenSemaphore = new Semaphore(2);
        this.oxygenSemaphore = new Semaphore(1);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        hydrogenSemaphore.acquire();

        releaseHydrogen.run();

        oxygenSemaphore.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        oxygenSemaphore.acquire(2);

        releaseOxygen.run();

        hydrogenSemaphore.release(2);

    }
}


/*
* Traditional way of doing it
class H2O {

    private int hCount;

    public H2O() {
        this.hCount = 0;
    }

    public synchronized void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        while (hCount == 2) {
            wait();
        }
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        hCount++;
        notifyAll();

    }

    public synchronized void oxygen(Runnable releaseOxygen) throws InterruptedException {

        while (hCount < 2) {
            wait();
        }
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        hCount = 0;
        notifyAll();
    }
}
*
* */
