### Introduction to SOLID Principles

SOLID is an acronym representing five principles of object-oriented programming and design that help developers create
software that is easy to maintain, extend, and understand. These principles were introduced by Robert C. Martin ("Uncle
Bob") and are foundational in achieving clean code and robust software architecture. By adhering to SOLID principles,
developers can ensure that their code is scalable, less prone to bugs, and easier to refactor.

The SOLID principles are:

1. **Single Responsibility Principle (SRP)**
2. **Open-Closed Principle (OCP)**
3. **Liskov Substitution Principle (LSP)**
4. **Interface Segregation Principle (ISP)**
5. **Dependency Inversion Principle (DIP)**

---

### 1. Single Responsibility Principle (SRP)

**Definition:** A class should have only one reason to change, meaning it should have only one responsibility.

**Explanation:** SRP ensures that a class focuses on a single task or responsibility. This makes the class easier to
understand, test, and maintain.

**Java Example:**

```java
// Violating SRP: A class handling both user data and email notifications
class UserService {
    public void saveUser(String user) {
        // Logic to save user to database
        System.out.println("User saved: " + user);
    }

    public void sendEmail(String email, String message) {
        // Logic to send email
        System.out.println("Email sent to: " + email);
    }
}

// Adhering to SRP: Separate responsibilities into distinct classes
class UserRepository {
    public void saveUser(String user) {
        // Logic to save user to database
        System.out.println("User saved: " + user);
    }
}

class EmailService {
    public void sendEmail(String email, String message) {
        // Logic to send email
        System.out.println("Email sent to: " + email);
    }
}

// Usage
UserRepository userRepository = new UserRepository();
EmailService emailService = new EmailService();
userRepository.saveUser("John Doe");
emailService.sendEmail("john.doe@example.com", "Welcome!");
```

**Benefits:**

- Clear separation of concerns.
- Easier testing since each class has a focused responsibility.
- Reduced coupling between unrelated functionalities.

---

### 2. Open-Closed Principle (OCP)

**Definition:** A class should be open for extension but closed for modification.

**Explanation:** OCP ensures that new functionality can be added to a system without altering existing code, reducing
the risk of introducing bugs.

**Java Example:**

```java
// Violating OCP: Modifying existing code for new behavior
class PaymentProcessor {
    public void processPayment(String paymentType) {
        if (paymentType.equals("CreditCard")) {
            System.out.println("Processing credit card payment");
        } else if (paymentType.equals("PayPal")) {
            System.out.println("Processing PayPal payment");
        }
    }
}

// Adhering to OCP: Using abstraction to allow extension
interface PaymentMethod {
    void processPayment();
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public void processPayment() {
        System.out.println("Processing credit card payment");
    }
}

class PayPalPayment implements PaymentMethod {
    @Override
    public void processPayment() {
        System.out.println("Processing PayPal payment");
    }
}

class PaymentProcessor {
    public void processPayment(PaymentMethod paymentMethod) {
        paymentMethod.processPayment();
    }
}

// Usage
PaymentProcessor processor = new PaymentProcessor();
processor.processPayment(new CreditCardPayment());
processor.processPayment(new PayPalPayment());
```

**Benefits:**

- Simplifies adding new payment methods without changing existing code.
- Enhances code maintainability and reduces the risk of regression bugs.

---

### 3. Liskov Substitution Principle (LSP)

**Definition:** Objects of a superclass should be replaceable with objects of a subclass without altering the
correctness of the program.

**Explanation:** LSP ensures that a subclass behaves in a way consistent with its superclass, adhering to the "is-a"
relationship.

**Java Example:**

```java
// Violating LSP: Subclass behavior deviates from superclass expectations
class Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}

class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        this.width = this.height = width;
    }

    @Override
    public void setHeight(int height) {
        this.width = this.height = height;
    }
}

// Correcting LSP: Avoid inheritance where "is-a" does not hold
interface Shape {
    int getArea();
}

class Rectangle implements Shape {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getArea() {
        return width * height;
    }
}

class Square implements Shape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public int getArea() {
        return side * side;
    }
}
```

**Benefits:**

- Prevents unexpected behavior when using polymorphism.
- Ensures a consistent interface for consumers of the superclass.

---

### 4. Interface Segregation Principle (ISP)

**Definition:** A class should not be forced to implement interfaces it does not use.

**Explanation:** ISP promotes creating smaller, more specific interfaces rather than a large, monolithic one.

**Java Example:**

```java
// Violating ISP: Large interface forces unused methods
interface Worker {
    void work();
    void eat();
}

class Robot implements Worker {
    @Override
    public void work() {
        System.out.println("Robot is working");
    }

    @Override
    public void eat() {
        // Robots do not eat
        throw new UnsupportedOperationException("Robots do not eat");
    }
}

// Adhering to ISP: Splitting into smaller interfaces
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {
    @Override
    public void work() {
        System.out.println("Human is working");
    }

    @Override
    public void eat() {
        System.out.println("Human is eating");
    }
}

class Robot implements Workable {
    @Override
    public void work() {
        System.out.println("Robot is working");
    }
}
```

**Benefits:**

- Reduces the burden of implementing unnecessary methods.
- Enhances flexibility and clarity in interface design.

---

### 5. Dependency Inversion Principle (DIP)

**Definition:** High-level modules should not depend on low-level modules. Both should depend on abstractions.
Abstractions should not depend on details; details should depend on abstractions.

**Explanation:** DIP decouples high-level and low-level modules, enabling easier testing and maintenance.

**Java Example:**

```java
// Violating DIP: High-level module depends on low-level module
class MySQLDatabase {
    public void connect() {
        System.out.println("Connected to MySQL database");
    }
}

class Application {
    private MySQLDatabase database;

    public Application() {
        this.database = new MySQLDatabase();
    }

    public void start() {
        database.connect();
    }
}

// Adhering to DIP: Using abstractions
interface Database {
    void connect();
}

class MySQLDatabase implements Database {
    @Override
    public void connect() {
        System.out.println("Connected to MySQL database");
    }
}

class Application {
    private Database database;

    public Application(Database database) {
        this.database = database;
    }

    public void start() {
        database.connect();
    }
}

// Usage
Database database = new MySQLDatabase();
Application app = new Application(database);
app.start();
```

**Benefits:**

- Promotes loose coupling.
- Simplifies swapping or mocking dependencies for testing.

### Summary

### SOLID Principles Table

| Principle                                 | Definition                                                                                             | Key Benefits                                                                                | Example Scenario                                                                                                                        |
|-------------------------------------------|--------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| **Single Responsibility Principle (SRP)** | A class should have only one reason to change, meaning it should have only one responsibility.         | - Clear separation of concerns - Easier testing - Reduced coupling between functionalities  | Splitting `UserService` into `UserRepository` for saving users and `EmailService` for sending emails.                                   |
| **Open-Closed Principle (OCP)**           | A class should be open for extension but closed for modification.                                      | - Simplifies adding new functionality - Enhances maintainability - Reduces regression bugs  | Using an interface like `PaymentMethod` to add new payment types without modifying the `PaymentProcessor`.                              |
| **Liskov Substitution Principle (LSP)**   | Objects of a superclass should be replaceable with objects of a subclass without altering correctness. | - Prevents unexpected behavior with polymorphism - Ensures consistency in subclass behavior | Avoiding inheritance where "is-a" does not hold, e.g., separating `Rectangle` and `Square` into independent implementations of `Shape`. |
| **Interface Segregation Principle (ISP)** | A class should not be forced to implement interfaces it does not use.                                  | - Reduces unnecessary method implementations - Increases flexibility and clarity            | Splitting a large `Worker` interface into smaller ones like `Workable` and `Eatable`.                                                   |
| **Dependency Inversion Principle (DIP)**  | High-level modules should not depend on low-level modules; both should depend on abstractions.         | - Promotes loose coupling - Easier testing and maintenance                                  | Using a `Database` interface to decouple the application from specific database implementations like `MySQLDatabase`.                   |

---

