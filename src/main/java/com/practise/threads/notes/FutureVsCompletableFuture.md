# Difference Between Future and CompletableFuture

## 1. Future:

### Basic Asynchronous Computation:

Future is a simple interface representing the result of an asynchronous computation. It allows you to check if the
computation is complete, wait for its completion, and retrieve the result.

### Limited API:

- **Blocking Get:** The `get()` method blocks until the computation is complete, which can lead to performance issues in
  applications if not handled carefully.
- **Cannot be Manually Completed:** Once a task is submitted to an `ExecutorService`, you cannot manually complete or
  cancel it outside the service's control.
- **No Built-in Exception Handling:** You have to handle exceptions manually after calling `get()`.

## 2. CompletableFuture:

### Enhanced Asynchronous Programming:

CompletableFuture extends Future with a much richer set of features, designed for more flexible and complex asynchronous
task handling.

### Non-Blocking and Composable:

- **Non-Blocking Operations:** Methods like `thenApply`, `thenAccept`, and `thenCompose` allow you to perform
  non-blocking operations, chaining multiple asynchronous tasks together.
- **Manual Completion:** You can manually complete a `CompletableFuture` using `complete()` or
  `completeExceptionally()`, providing more control over task execution.
- **Built-in Exception Handling:** Methods like `exceptionally` and `handle` allow for more elegant handling of
  exceptions.
- **Combining Futures:** `CompletableFuture` can combine multiple futures using `thenCombine`, `thenAcceptBoth`,
  `allOf`, and `anyOf`, allowing for complex task orchestration.

## Why CompletableFuture Was Introduced

CompletableFuture was introduced in Java 8 to address the limitations of Future and to align with the functional
programming paradigm introduced in the same version. It provides:

- **Better Composition:** Allows chaining of tasks and handling of asynchronous operations in a more readable and
  maintainable way.
- **Functional Style:** Integrates with lambda expressions and functional interfaces, supporting a more declarative
  approach to asynchronous programming.
- **Improved Error Handling:** Offers built-in mechanisms to handle exceptions, making the code more robust and less
  error-prone.
- **Non-Blocking and Reactive:** Supports non-blocking operations, which are crucial for building scalable and
  responsive applications.

## Trade-offs Between Future and CompletableFuture

### Complexity vs. Simplicity:

- **Future:** Simpler and might be easier to understand for basic use cases. It's suitable for straightforward
  asynchronous tasks where you just need to know when a task is complete and retrieve its result.
- **CompletableFuture:** More complex but offers a richer API for handling sophisticated asynchronous workflows.

### Control and Flexibility:

- **CompletableFuture:** Provides more control over task execution, allowing you to manually complete tasks and handle
  exceptions more gracefully.
- **Future:** Lacks these capabilities, limiting its use in more complex scenarios.

### Performance Considerations:

- **CompletableFuture:** May have a slight performance overhead due to its additional features, but this is often
  outweighed by the benefits in terms of code clarity and maintainability.
- **Future:** For simple tasks, might be more lightweight, but it requires careful handling to avoid blocking issues.

### API Compatibility:

- **CompletableFuture:** Available from Java 8 onwards, so projects on older Java versions cannot use it.
- **Future:** Has been available since Java 5, making it compatible with a wider range of environments.

## Summary

CompletableFuture was introduced to provide a more powerful and flexible framework for asynchronous programming,
addressing the limitations of Future by offering non-blocking operations, better composition, and enhanced error
handling. The choice between them depends on the complexity of the task at hand and the specific needs of your
application.