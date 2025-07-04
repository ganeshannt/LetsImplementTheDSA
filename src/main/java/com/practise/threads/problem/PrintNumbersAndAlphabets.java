package com.practise.threads.problem;


/*
*
* output:
1A2B3C4D5E6F7G8
*
* */
public class PrintNumbersAndAlphabets {

    private static final int MAX = 26;
    private boolean printLetter = false;
    private int counter = 1;

    public static void main(String[] args) {

        PrintNumbersAndAlphabets p = new PrintNumbersAndAlphabets();

        Thread t1 = new Thread(p::printAlphabets);
        Thread t2 = new Thread(p::printNumber);

        t1.start();
        t2.start();

    }


    synchronized void printAlphabets() {
        while (counter <= MAX) {
            while (!printLetter) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            char c = (char) ('A' + counter - 1);

            System.out.println(Character.valueOf(c));
            counter++;
            printLetter = false;
            notify();
        }
    }


    synchronized void printNumber() {
        while (counter <= MAX) {
            while (printLetter) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(counter);
            printLetter = true;
            notify();
        }
    }
}
