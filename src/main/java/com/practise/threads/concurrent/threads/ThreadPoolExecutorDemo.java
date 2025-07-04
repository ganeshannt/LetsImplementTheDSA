package com.practise.threads.concurrent.threads;

import java.util.concurrent.*;


/*
*
*
*
* Disadvantages of using ThreadPoolExecutor:
    1. Forgetting to shut down the ExecutorService
    2. Blocking Indefinitely with Future.get()
    3. Swallowed Exceptions (if Future.get() isn't called or handled)
    4. ThreadLocal State Leakage
    5. Misconfigured Thread Pools (e.g., unbounded queues or too many threads)
* */

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        // Create a ThreadPoolExecutor with a fixed pool size of 3

        //try-with-resources to ensure the executor is properly shut down
        try (ExecutorService executor = Executors.newFixedThreadPool(3)) {

            // Submit tasks for execution
            for (int i = 1; i <= 100; i++) {
                int taskNumber = i;
                executor.execute(() -> System.out.println("Task " + taskNumber + " executed by " + Thread.currentThread().getName()));
            }


            Future<Integer> task = executor.submit(() -> {
                System.out.println("Executing a Callable task on " + Thread.currentThread().getName());
                // Simulate some computation
                int sum = 0;
                for (int i = 1; i <= 1000; i++) {
                    sum += i;
                }
                Thread.sleep(3000); // Simulate a delay
                return sum;
            });

            System.out.println("Result from submitted task: " + task.get(3, TimeUnit.SECONDS));

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
