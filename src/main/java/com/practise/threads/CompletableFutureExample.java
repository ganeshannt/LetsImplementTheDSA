package com.practise.threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("started executing " + Thread.currentThread().getName());

        CompletableFutureExample completableFutureExample = new CompletableFutureExample();

        long start = System.currentTimeMillis();

        /*
         * Internally, it will create a Thread using Fork Join Pool. Many things handled internally so code looks cleaner compared to the Future.
         * CompletableFuture is a higher-level API than Future and it is used for asynchronous programming in Java.
         * Reasons to prefer CompletableFuture over Future:
         * 1. CompletableFuture can be manually completed using complete(), completeExceptionally(), and cancel() methods.
         * 2. CompletableFuture provides a built-in exception handling mechanism.
         * 3. CompletableFuture provides a way to run multiple CompletableFutures in parallel using thenCompose(), thenCombine(), and thenAcceptBoth() methods. (method chaining like streams).
         * 4. CompletableFuture provides a way to handle the result of the computation using thenApply(), thenAccept(), and thenRun() methods.
         *
         * */
        CompletableFuture<String> getFirstNameCompletableFuture = CompletableFuture.supplyAsync(() -> completableFutureExample.getFirstName());
        CompletableFuture<String> getLastNameCompletableFuture = CompletableFuture.supplyAsync(() -> completableFutureExample.getLastName());


        CompletableFuture<String> completeNameCompletableFuture = getFirstNameCompletableFuture.thenApply(String::toUpperCase).thenCombine(getLastNameCompletableFuture, (firstName, lastName) -> {
                    System.out.println("Combining first name and last name: Thread: " + Thread.currentThread().getName());
                    return firstName + " " + lastName;
                }
        );

        System.out.println(completeNameCompletableFuture.get());
        System.out.println("time taken to execute: " + (System.currentTimeMillis() - start) + "ms");
    }

    public String getFirstName() {
        try {
            System.out.println("Getting first name: Thread: " + Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Ganeshan";
    }

    public String getLastName() {
        try {
            System.out.println("Getting last name: Thread: " + Thread.currentThread().getName());
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Nagarajan";
    }
}
