package com.practise.threads;


import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 Extend Thread class when we want to override run method and no need to extend another class or multiple inheritance
 (not supported because of ambiguity).
 */
class Thread1 extends Thread {
    Thread1(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Extends Thread Class : " + Thread.currentThread().getName() + " : " + i);
        }
    }
}

/*
 * implementing Runnable interface when we want to extend another class and to avoid multiple inheritance.
 * */
class Thread2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Implements Runnable Interface : " + Thread.currentThread().getName() + " : " + i);
        }
    }
}

/*
 * Callable is suitable when we want to return a result and throw an exception.
 * */
class Thread3 implements Callable<Integer> {

    List<Integer> numbers;

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Implements Callable Interface : " + Thread.currentThread().getName());
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }
}


public class ThreadTester {

    public static void main(String[] args) {
        System.out.println("LambdaTester thread starting");

        /*
         * LambdaTester difference between Thread and Runnable is that Thread is a class and Runnable is an interface.
         * Thread class implements Runnable interface and internally calls run method of Runnable interface,
         * but if you extend Thread class, then you cannot extend any other class.
         * */
        // extend Thread class and override run method
        Thread1 t1 = new Thread1("Thread1");
        t1.start();

        // implement Runnable interface and override run method.
        // object passed as argument to Thread class constructor because Thread class constructor accepts Runnable object (Thread2 class implements Runnable) and not Thread object.
        Thread t2 = new Thread(new Thread2(), "Thread2");
        t2.start();

        // Callable returns a result and throw an exception but runnable won't.
        // Callable is a functional interface and can be used with lambda expression same as runnable
        // Callable can't be passed to Thread class, whereas Runnable can be passed to Thread class.
        // Why does Thread class constructor accept a Runnable object?
        // Because Thread class implements Runnable interface and internally calls run method of Runnable interface.
        Thread3 t3 = new Thread3();
        t3.setNumbers(List.of(1, 2, 3, 4, 5));
        int sum;
        try {
            sum = t3.call();
            System.out.println(sum);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // you can submit a callable interface task to executor service and get the result.
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Integer> total = executorService.submit(t3);
        System.out.println(total);
        executorService.submit(t2);
        executorService.shutdown(); // it will stop the executor service after all tasks are completed.


        /*
            The difference becomes clear when we look at what's happening at the JVM level:
            1.start() tells the JVM to create a new thread, set up its resources, and then call run() on that new thread.
            2.run() is just a regular method call, executed on whatever thread it's called from.
            3.This is why start() gives you true concurrent execution, while run() does not.


            4.With start(), you'll see interleaved output from Thread-A and Thread-B, demonstrating true concurrency. If you used run() instead, you'd see all of Thread-A's output, followed by all of Thread-B's output, all on the main thread.

            5.In essence, start() is the gateway to multi-threading, while run() is just a regular method call. The magic of start() is in how it instructs the JVM to set up a new thread before invoking run().


         */

        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": [t.start() vs t.run()] " + i);
                try {
                    Thread.sleep(100); // Simulate some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t4 = new Thread(task, "Thread-A");
        Thread t5 = new Thread(task, "Thread-B");

//        t4.start(); // Creates and starts a new thread
//        t5.start(); // Creates and starts another new thread

        // Compare with:
        t4.run(); // Would run sequentially in the main thread
        t5.run(); // Would also run sequentially in the main thread

        System.out.println("LambdaTester thread exiting");
    }

}
