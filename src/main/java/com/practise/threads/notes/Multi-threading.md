# Understanding `thread.start()` vs `thread.run()`

## Why `thread.start()` and `thread.run()` behave so differently, even though `start()` ultimately calls `run()`.

The key difference lies in how and where the `run()` method is called.

### `thread.start()`

When you call `start()`, here's what happens behind the scenes:

1. The Java Virtual Machine (JVM) creates a new thread of execution.
2. This new thread gets its own call stack.
3. The JVM then calls the `run()` method on this new thread and its new call stack.

```java
Thread thread = new Thread(() -> {
    System.out.println("Running in thread: " + Thread.currentThread().getName());
});
thread.start(); // New thread created, then run() called on that thread
System.out.println("Main thread: "+Thread.currentThread().getName());
```

## why thread.start() and thread.run() behave so differently, even though start() ultimately calls run().

The key difference lies in how and where the run() method is called.

thread.start():
When you call start(), here's what happens behind the scenes:

- The Java Virtual Machine (JVM) creates a new thread of execution.
- This new thread gets its own call stack.
- The JVM then calls the run() method on this new thread and its new call stack.

```java

Thread thread = new Thread(() -> {
    System.out.println("Running in thread: " + Thread.currentThread().getName());
});
thread.start(); // New thread created, then run() called on that thread
System.out.println("Main thread: "+Thread.currentThread().getName());
```

Output (order may vary):

Main thread: main
Running in thread: Thread-0

thread.run():

When you directly call run(), it's just a normal method call:

- No new thread is created.
- The run() method executes on the current thread's call stack.
- It behaves like any other method call in your program.

```java

Thread thread = new Thread(() -> {
    System.out.println("Running in thread: " + Thread.currentThread().getName());
});
thread.run(); // Direct method call, no new thread
System.out.println("Main thread: "+Thread.currentThread().getName());
```

Output (always in this order):

Running in thread: main
Main thread: main

## The difference becomes clear when we look at what's happening at the JVM level:

1. start() tells the JVM to create a new thread, set up its resources, and then call run() on that new thread.
2. run() is just a regular method call, executed on whatever thread it's called from.
3. This is why start() gives you true concurrent execution, while run() does not.

To further illustrate:

```java

public class ThreadDemo {
    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    Thread.sleep(100); // Simulate some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(task, "Thread-A");
        Thread t2 = new Thread(task, "Thread-B");

        t1.start(); // Creates and starts a new thread
        t2.start(); // Creates and starts another new thread

        // Compare with:
        // t1.run(); // Would run sequentially in the main thread
        // t2.run(); // Would also run sequentially in the main thread
    }
}
```

With start(), you'll see interleaved output from Thread-A and Thread-B, demonstrating true concurrency.
If you used run() instead, you'd see all of Thread-A's output, followed by all of Thread-B's output, all on the main
thread.

In essence, start() is the gateway to multi-threading, while run() is just a regular method call.
The magic of start() is in how it instructs the JVM to set up a new thread before invoking run().