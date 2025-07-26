package com.practise.threads.problem;

/**
 * Problem: Print even and odd numbers in increasing order using two threads in Java
 * 
 * Description: Implement a multi-threaded program where two threads take turns printing 
 * odd and even numbers in increasing order. One thread prints only odd numbers, and the 
 * other thread prints only even numbers.
 * 
 * Platform Link: N/A (Common concurrency interview problem)
 * 
 * Approach: 
 * - Use synchronized methods to ensure thread safety
 * - Use wait() and notify() for thread communication
 * - Each thread checks if it's its turn to print, otherwise waits
 * 
 * Time Complexity: O(n) where n is the maximum number to print
 * Space Complexity: O(1) - Uses constant extra space
 * 
 * Concurrency Considerations:
 * - Thread synchronization using intrinsic locks (synchronized keyword)
 * - Inter-thread communication using wait() and notify()
 * - Potential for deadlock if not implemented correctly
 * 
 * @author Ganeshan Nagarajan
 * @since 25/12/22
 */
public class OddEvenPrint {

    private static final int MAX = 10;
    private int count = 1;

    /**
     * Main method to demonstrate the odd-even printing using two threads.
     * Creates and starts two threads, one for printing even numbers and one for odd numbers.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        OddEvenPrint oep = new OddEvenPrint();
        Thread t1 = new Thread(oep::printEven);
        Thread t2 = new Thread(oep::printOdd);

        t1.start();
        t2.start();
    }

    /**
     * Prints odd numbers in sequence.
     * This method is executed by one thread and prints only odd numbers.
     * It waits when it's not its turn (when count is even).
     * 
     * Thread Safety: Uses synchronized block and wait/notify mechanism
     * Time Complexity: O(n) where n is MAX
     */
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

    /**
     * Prints even numbers in sequence.
     * This method is executed by another thread and prints only even numbers.
     * It waits when it's not its turn (when count is odd).
     * 
     * Thread Safety: Uses synchronized block and wait/notify mechanism
     * Time Complexity: O(n) where n is MAX
     */
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


