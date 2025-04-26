package com.practise.threads;

import java.util.concurrent.*;


/*
Future Limitations:
- Blocking Get: The get() method blocks until the computation is complete, which can lead to performance issues in applications if not handled carefully.
- Cannot be Manually Completed: Once a task is submitted to an ExecutorService, you cannot manually complete or cancel it outside the service's control.
- No Built-in Exception Handling: You have to handle exceptions manually after calling get().
* */

public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("started executing " + Thread.currentThread().getName());

        FutureExample futureExample = new FutureExample();

        long start = System.currentTimeMillis();

        Callable<String> firstNameCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Getting first name: Thread: " + Thread.currentThread().getName());
                return futureExample.getFirstName();
            }
        };

        Callable<String> lastNameCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Getting last name: Thread: " + Thread.currentThread().getName());
                return futureExample.getLastName();
            }
        };


        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> futureFirstName = executorService.submit(firstNameCallable); // run the task asynchronously
        Future<String> futureLastName = executorService.submit(lastNameCallable); // run the task asynchronously


        String firstName = futureFirstName.get(); // blocking call
        String lastName = futureLastName.get(); // blocking call

        String completeName = firstName + " " + lastName;

        System.out.println(completeName);
        System.out.println("time taken to execute: " + (System.currentTimeMillis() - start) + "ms");
        executorService.shutdown();
    }

    public String getFirstName() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Ganeshan";
    }

    public String getLastName() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Nagarajan";
    }
}
