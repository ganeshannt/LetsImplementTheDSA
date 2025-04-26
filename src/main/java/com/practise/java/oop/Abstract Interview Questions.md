# Interview Questions

## What is the difference between abstract class and concrete class?

## What is Abstract method in Java?

A method which is declared with the `abstract` modifier and has no implementation (means no body) is called an abstract
method in Java. It does not contain any body. It has simply a signature declaration followed by a semicolon. It has the
following general form as given below.

## Can an abstract method be declared as static? Why?

No, an abstract method cannot be declared as static in Java. This is because abstract methods are designed to be
overridden by subclasses, and static methods are not subject to overriding. Static methods belong to the class itself,
rather than to any particular instance of the class, and can be called without creating an instance of the class.
However, abstract methods must be implemented by subclasses, and so they require an instance of the subclass to be
created in order to be used. Therefore, since static methods and abstract methods serve different purposes, it is not
possible to declare an abstract method as static.

## Can an abstract method be declared with private modifier?

No, it cannot be private because the abstract method must be implemented in the child class. If we declare it as
private, we cannot implement it from outside the class.

## When to use Abstract class in Java?

Abstract classes in Java are useful when you want to define a common interface for a group of related classes, while
allowing each subclass to provide its own implementation. Here are a few situations where you might want to use an
abstract class:

- When you have a group of related classes that share some common behavior, but also have some unique behavior that
  cannot be generalized. In this case, you can define the common behavior in the abstract class, and leave the unique
  behavior to be implemented by the subclasses.

## When to use Abstract method in Java?

An abstract method can be used when:

- The same method has to perform different tasks depending on the object calling it.
- You need to be overridden in its non-abstract subclasses.

## Can an abstract class have constructor? Why?

Yes, an abstract class can have a constructor in Java. A constructor is a special method that is used to initialize the
state of an object when it is created. Even though an abstract class cannot be instantiated directly, it can still be
used as a superclass for its concrete subclasses, which can be instantiated. When a subclass object is created, the
constructor of the superclass is called to initialize the common attributes inherited from the superclass. Therefore, an
abstract class may need to have a constructor to initialize these common attributes, which will be inherited by its
subclasses.

## Is it possible to achieve multiple inheritance through abstract class? Why?

Yes, it is possible to achieve multiple inheritance through abstract class in Java. This is because an abstract class
can implement multiple interfaces, and each of these interfaces can have its own set of methods that must be implemented
by the abstract class. By doing so, the abstract class can inherit functionality from multiple sources, effectively
achieving multiple inheritance. However, it is important to note that Java does not support multiple inheritance of
classes, which means that a class cannot extend more than one class. This is because multiple inheritance of classes can
lead to the diamond problem, where the same method is inherited from two different parent classes, leading to ambiguity
in the implementation. By using abstract classes and interfaces, Java provides a way to achieve some of the benefits of
multiple inheritance, while avoiding the problems that can arise from multiple inheritance of classes.

## What is the difference between Abstraction and Encapsulation?

Abstraction and encapsulation are related concepts, but they are not the same thing. Abstraction is the process of
hiding implementation details, while encapsulation is the process of hiding data or implementation details within a
class. Abstraction is a more general concept, while encapsulation is a more specific one. In other words, abstraction is
a way to manage complexity, while encapsulation is a way to protect data.

## Can we instantiate a class which does not have even a single abstract methods but declared as abstract?

No, we can’t instantiate a class once it is declared as abstract even though it does not have abstract methods.

## Why final and abstract can not be used at a time?

Because `final` and `abstract` are totally opposite in nature. A `final` class or method cannot be modified further
whereas an `abstract` class or method must be modified further. The `final` keyword is used to denote that a class or
method does not need further improvements. The `abstract` keyword is used to denote that a class or method needs further
improvements.

## What are some common design patterns that make use of abstraction?

There are many design patterns that make use of abstraction, including the Factory Pattern, the Strategy Pattern, and
the Observer Pattern. These patterns all make use of interfaces or abstract classes to define a common set of methods
that can be implemented by multiple classes.

## What are the advantages and disadvantages of using abstraction in software development?

**Advantages:**

- Improved code reuse
- Increased modularity
- Improved maintainability

**Disadvantages:**

- Increased complexity and overhead
- Potential for over-engineering

## What are some best practices for using abstraction in your code?

Some best practices for using abstraction in your code include:

- Defining clear and concise interfaces or abstract classes
- Avoiding over-engineering
- Favoring composition over inheritance

Additionally, it is important to test your classes and interfaces thoroughly to ensure that they are working as
intended.

## Conclusion

By mastering the art of abstraction, you can create efficient and organized programs, improve the readability and
maintainability of your code, and facilitate collaboration with other developers. The expert-recommended questions on
abstraction provided in this article serve as a valuable resource for Java developers who want to deepen their
understanding of the topic and prepare for interviews. With sufficient practice and preparation, you can confidently
approach Java OOP interviews and demonstrate your expertise in abstraction.