package com.practise.threads.problem;

/**
 * @author Ganeshan Nagarajan
 * @since 25/12/22
 */


/*
Name - Print even and odd numbers in increasing order using two threads in Java
Link - NA
*/

public class OddEvenPrint {

    private static final int MAX = 10;
    private int count = 1;

    public static void main(String[] args) {

        OddEvenPrint oep = new OddEvenPrint();
        Thread t1 = new Thread(() -> oep.printEven());
        Thread t2 = new Thread(() -> oep.printOdd());

        t1.start();
        t2.start();

    }

    public void printOdd() {
        synchronized (this) {
            while (count < MAX) {
                while (count % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Odd Thread :" + count);
                count++;
                notify();
            }
        }
    }

    public void printEven() {
        synchronized (this) {
            while (count < MAX) {
                while (count % 2 != 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Even thread :" + count);
                count++;
                notify();
            }
        }
    }
}


