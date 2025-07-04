package com.practise.threads.problem;


import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;

    private boolean isZeroPrinted;
    private boolean isOddPrinted;
    private boolean isEvenPrinted;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.isZeroPrinted = false;
        this.isOddPrinted = false;
        this.isEvenPrinted = false;
    }


    public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            while (isZeroPrinted) {
                wait();
            }

            if (i % 2 == 0) {
                isEvenPrinted = false;
                isOddPrinted = true;
            } else {
                isEvenPrinted = true;
                isOddPrinted = false;
            }

            printNumber.accept(0);
            isZeroPrinted = true;
            notifyAll();
        }
    }

    public synchronized void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            while (isEvenPrinted) {
                wait();
            }
            printNumber.accept(i);
            isZeroPrinted = false;
            isEvenPrinted = true;
            notifyAll();
        }
    }

    public synchronized void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            while (isOddPrinted) {
                wait();
            }
            printNumber.accept(i);
            isZeroPrinted = false;
            isOddPrinted = true;
            notifyAll();
        }
    }
}
