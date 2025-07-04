package com.practise.threads;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/*
* What is ThreadLocal?
ThreadLocal is a Java class that provides thread-local variables.
    * Each thread accessing such a variable has its own, independently initialized copy of the variable.
    * This ensures that the variable is not shared between threads,
    * making it useful for maintaining thread-specific data.
When and Where to Use ThreadLocal
Use Cases:
Thread-Specific Context: Store data that is specific to a thread, such as user session information, transaction IDs, or request metadata.
Avoid Passing Data Explicitly:
* Useful when you want to avoid passing data explicitly through method calls in multithreaded environments.
Thread-Safe Caching:
* Cache objects like database connections or formatters (e.g., SimpleDateFormat) that are not thread-safe.
Frameworks: Commonly used in frameworks like Spring for managing request-scoped or session-scoped beans.
When to Avoid:
Shared Data: If the data needs to be shared across threads, ThreadLocal is not suitable.
Memory Leaks: Improper cleanup of ThreadLocal variables in thread pools can lead to memory leaks.
Complexity: Avoid using ThreadLocal if simpler alternatives like method parameters or thread-safe collections suffice.
Pros and Cons of ThreadLocal
Pros:
Thread Safety: Provides thread-local storage, avoiding synchronization overhead.
Encapsulation: Hides thread-specific data, reducing the need to pass data explicitly.
Performance: Reduces contention in multi-threaded environments by isolating data per thread.
Cons:
Memory Leaks: If not cleaned up properly, ThreadLocal can cause memory leaks, especially in thread pools.
Debugging Complexity: Makes debugging harder as the data is hidden and not shared.
Limited Scope: Only useful for thread-specific data, not for shared or global data.

Usage in Spring
Request and Session Scopes: Spring uses ThreadLocal internally to manage request and session-scoped beans.
SecurityContextHolder: Spring Security uses ThreadLocal to store the SecurityContext for the current thread.
Transaction Management: Spring uses ThreadLocal to manage transaction contexts in a thread-safe manner.

Java Built-In Functions Using ThreadLocal
ThreadLocal.withInitial(Supplier<? extends T>): Creates a ThreadLocal with an initial value provided by a Supplier.
get(): Retrieves the current thread's value of the ThreadLocal variable.
set(T value): Sets the value for the current thread.
remove(): Removes the value for the current thread to prevent memory leaks.
*
* */


record UserContext(String userId, String role) {
}


class ThreadLocalDemo {

    // --- ThreadLocal Variables ---

    // 1. Transaction ID: Unique per thread execution, no initial value needed here
    //    We will set it explicitly at the start of the task.
    private static final ThreadLocal<String> transactionId = new ThreadLocal<>();

    // 2. User Context: Holds user info for the current thread's request.
    //    Using withInitial for a default "Guest" user if not set.
    private static final ThreadLocal<UserContext> userContext =
            ThreadLocal.withInitial(() -> new UserContext("guest", "GUEST"));

    // 3. Processing Step Counter: Tracks steps within a thread's task.
    //    Starts at 0 for each thread's task execution.
    private static final ThreadLocal<AtomicInteger> stepCounter =
            ThreadLocal.withInitial(() -> new AtomicInteger(0));

    // 4. Inheritable Security Context: For passing data to child threads.
    private static final InheritableThreadLocal<String> securityContext =
            new InheritableThreadLocal<>();

    // --- Main Execution Logic ---

    public static void main(String[] args) {
        System.out.println("Starting ThreadLocal Demo...");

        // Using a fixed thread pool to simulate thread reuse (common in servers)
        // This makes proper cleanup with remove() absolutely critical!
        ExecutorService executor = Executors.newFixedThreadPool(2); // Small pool to show reuse

        // Simulate 5 incoming requests
        for (int i = 1; i <= 5; i++) {
            final int requestId = i;
            executor.submit(() -> processRequest(requestId));
        }

        // Shutdown the executor gracefully
        System.out.println("Shutting down executor...");
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.err.println("Executor did not terminate in time. Forcing shutdown.");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println("Executor termination interrupted.");
            executor.shutdownNow();
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }

        System.out.println("Demo finished.");
    }

    // --- Request Processing Logic ---

    public static void processRequest(int requestId) {
        // **IMPORTANT: Setup Phase - Set ThreadLocal values for this task**
        String currentTransactionId = UUID.randomUUID().toString();
        transactionId.set(currentTransactionId);
        userContext.set(new UserContext("user-" + requestId, "REGISTERED")); // Set specific user
        securityContext.set("ConfidentialityLevel-" + requestId); // Set inheritable context
        // stepCounter is initialized via withInitial, no need to set to 0 explicitly here

        String threadName = Thread.currentThread().getName();
        System.out.printf("[%s] === Starting Request %d (TxID: %s) ===\n",
                threadName, requestId, transactionId.get());

        try {
            // Simulate processing steps
            performStep(threadName, "Validation");
            performStep(threadName, "Data Fetching");

            // Simulate spawning a child thread (sub-task)
            spawnSubTask(threadName);

            performStep(threadName, "Processing");
            performStep(threadName, "Logging");

            System.out.printf("[%s] === Finished Request %d (TxID: %s), Steps: %d, User: %s ===\n",
                    threadName, requestId, transactionId.get(), stepCounter.get().get(), userContext.get());

        } catch (Exception e) {
            System.err.printf("[%s] **** ERROR processing Request %d (TxID: %s): %s ****\n",
                    threadName, requestId, transactionId.get(), e.getMessage());
        } finally {
            // **CRITICAL: Cleanup Phase - Remove ThreadLocal values**
            // This prevents memory leaks when threads are reused in the pool.
            // If you comment these out, values from previous tasks might linger
            // or, worse, prevent objects from being garbage collected.
            System.out.printf("[%s] --- Cleaning up ThreadLocals for Request %d (TxID: %s) ---\n",
                    threadName, requestId, transactionId.get());
            transactionId.remove();
            userContext.remove(); // Resets to initial value for the *next* task using this thread
            stepCounter.remove(); // Resets to initial value (0) for the next task
            securityContext.remove();
        }
    }

    // --- Helper Methods ---

    private static void performStep(String threadName, String stepName) {
        int currentStep = stepCounter.get().incrementAndGet();
        System.out.printf("[%s] Step %d: %s (User: %s, TxID: %s)\n",
                threadName, currentStep, stepName, userContext.get().userId(), transactionId.get());
        // Simulate work
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void spawnSubTask(String parentThreadName) {
        System.out.printf("[%s] Spawning sub-task...\n", parentThreadName);

        Thread subTaskThread = new Thread(() -> {
            String subThreadName = Thread.currentThread().getName();
            System.out.printf("  [%s] (Sub-Task) Started.\n", subThreadName);

            // 1. Access Regular ThreadLocals (Should get initial/default values or null)
            //    These are NOT inherited by default.
            String inheritedTxId = transactionId.get(); // Will likely be null (or value from previous use if cleanup failed!)
            UserContext inheritedUser = userContext.get(); // Will be the initial "guest" user
            int inheritedStepCount = stepCounter.get().get(); // Will be the initial 0

            System.out.printf("  [%s] (Sub-Task) Regular TxID: %s (Expected: null or stale)\n", subThreadName, inheritedTxId);
            System.out.printf("  [%s] (Sub-Task) Regular User: %s (Expected: guest)\n", subThreadName, inheritedUser);
            System.out.printf("  [%s] (Sub-Task) Regular Step Count: %d (Expected: 0)\n", subThreadName, inheritedStepCount);

            // 2. Access InheritableThreadLocal (Should get the value from the parent thread)
            String inheritedSecurity = securityContext.get();
            System.out.printf("  [%s] (Sub-Task) Inherited Security Context: %s (Expected: Parent's value)\n", subThreadName, inheritedSecurity);

            // Simulate sub-task work
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // **Important Note:** If this sub-task also set ThreadLocals,
            // it would need its own cleanup (`remove()`) calls in a finally block.
            // Also, cleaning up InheritableThreadLocal in the child *might* affect siblings
            // if the parent creates multiple children sequentially without resetting the value.
            // Use InheritableThreadLocal with care regarding cleanup lifecycle.

            System.out.printf("  [%s] (Sub-Task) Finished.\n", subThreadName);
        });

        subTaskThread.setName(parentThreadName + "-SubTask");
        subTaskThread.start();
        try {
            subTaskThread.join(); // Wait for the sub-task to complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.printf("[%s] Sub-task finished.\n", parentThreadName);
    }
}

